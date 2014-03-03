package br.com.sapientia.vegetabilia.dao;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import br.com.sapientia.vegetabilia.models.Medida;
import br.com.sapientia.vegetabilia.utils.DAOSample;

import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("unchecked")
public class MedidaDAO extends DAOSample {

	public boolean salvar(Medida m) {
		boolean r = false;

		try {
			this.start();

			this.sessao.save(m);

			this.commit();

			r = true;
		} catch (HibernateException e) {
			this.rollbackTrans(e);
		} finally {
			try {
				this.closeSession();
			} catch (Exception e2) {
				this.sessionException(e2);
			}
		}

		return r;
	}

	public boolean editar(Medida m) {
		boolean r = false;

		try {
			this.start();

			this.sessao.update(m);

			this.commit();

			r = true;
		} catch (HibernateException e) {
			this.rollbackTrans(e);
		} finally {
			try {
				this.closeSession();
			} catch (Exception e2) {
				this.sessionException(e2);
			}
		}

		return r;
	}

	public boolean excluir(Medida m) {
		boolean r = false;

		try {
			this.start();

			this.sessao.delete(m);

			this.commit();

			r = true;
		} catch (HibernateException e) {
			this.rollbackTrans(e);
		} finally {
			try {
				this.closeSession();
			} catch (Exception e2) {
				this.sessionException(e2);
			}
		}

		return r;
	}

	public BeanItemContainer<Medida> listar() {
		BeanItemContainer<Medida> container = null;

		try {
			this.start();

			container = new BeanItemContainer<Medida>(Medida.class, this.sessao
					.createCriteria(Medida.class).addOrder(Order.asc("nome"))
					.list());

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
