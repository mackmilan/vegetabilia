package br.com.sapientia.vegetabilia.dao;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import com.vaadin.data.util.BeanItemContainer;

import br.com.sapientia.vegetabilia.models.Despesa;
import br.com.sapientia.vegetabilia.utils.DAOSample;

@SuppressWarnings("unchecked")
public class DespesaDAO extends DAOSample {

	public boolean salvar(Despesa d) {
		boolean r = false;

		try {
			this.start();

			this.sessao.save(d);

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

	public boolean editar(Despesa d) {
		boolean r = false;

		try {
			this.start();

			this.sessao.update(d);

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

	public boolean excluir(Despesa d) {
		boolean r = false;

		try {
			this.start();

			this.sessao.delete(d);

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

	public BeanItemContainer<Despesa> listar() {
		BeanItemContainer<Despesa> container = null;

		try {
			this.start();

			container = new BeanItemContainer<Despesa>(Despesa.class,
					this.sessao.createCriteria(Despesa.class)
							.addOrder(Order.desc("data")).list());

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
