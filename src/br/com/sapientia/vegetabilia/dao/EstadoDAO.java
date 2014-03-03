package br.com.sapientia.vegetabilia.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import com.vaadin.data.util.BeanItemContainer;

import br.com.sapientia.vegetabilia.models.Estado;
import br.com.sapientia.vegetabilia.utils.DAOSample;

public class EstadoDAO extends DAOSample {

	@SuppressWarnings("unchecked")
	public BeanItemContainer<Estado> listar() {
		BeanItemContainer<Estado> container = null;
		List<Estado> lista = null;

		try {
			this.start();

			lista = this.sessao.createCriteria(Estado.class)
					.addOrder(Order.asc("nome")).list();
			container = new BeanItemContainer<Estado>(Estado.class, lista);

			this.commit();
		} catch (HibernateException e) {
			this.rollbackTrans(e);
		} finally {
			try {
				this.closeSession();
			} catch (Exception e2) {
				this.sessionException(e2);
			}
		}

		return container;
	}
}
