package br.com.sapientia.vegetabilia.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.vaadin.data.util.BeanItemContainer;

import br.com.sapientia.vegetabilia.models.Cidade;
import br.com.sapientia.vegetabilia.utils.DAOSample;

public class CidadeDAO extends DAOSample {

	@SuppressWarnings("unchecked")
	public BeanItemContainer<Cidade> listar(int cod) {
		BeanItemContainer<Cidade> container = null;
		List<Cidade> lista = null;

		try {
			this.start();

			this.hql = "from Cidade where estado.codEstado = :cod";
			this.consulta = this.sessao.createQuery(hql);
			this.consulta.setInteger("cod", cod);

			lista = consulta.list();
			container = new BeanItemContainer<Cidade>(Cidade.class, lista);

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
