package br.com.sapientia.vegetabilia.dao;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import com.vaadin.data.util.BeanItemContainer;

import br.com.sapientia.vegetabilia.models.Entrada;
import br.com.sapientia.vegetabilia.utils.DAOSample;

@SuppressWarnings("unchecked")
public class EntradaDAO extends DAOSample {

	public boolean salvar(Entrada e) {
		boolean r = false;

		try {
			this.start();

			this.sessao.save(e);

			this.commit();

			r = true;
		} catch (HibernateException e2) {
			this.rollbackTrans(e2);
		} finally {
			try {
				this.closeSession();
			} catch (Exception e3) {
				this.sessionException(e3);
			}
		}

		return r;
	}

	public boolean editar(Entrada e) {
		boolean r = false;

		try {
			this.start();

			this.sessao.update(e);

			this.commit();

			r = true;
		} catch (HibernateException e2) {
			this.rollbackTrans(e2);
		} finally {
			try {
				this.closeSession();
			} catch (Exception e3) {
				this.sessionException(e3);
			}
		}

		return r;
	}

	public boolean excluir(Entrada e) {
		boolean r = false;

		try {
			this.start();

			this.sessao.delete(e);

			this.commit();

			r = true;
		} catch (HibernateException e2) {
			this.rollbackTrans(e2);
		} finally {
			try {
				this.closeSession();
			} catch (Exception e3) {
				this.sessionException(e3);
			}
		}

		return r;
	}

	public BeanItemContainer<Entrada> listar() {
		BeanItemContainer<Entrada> container = null;

		try {
			this.start();

			container = new BeanItemContainer<Entrada>(Entrada.class,
					this.sessao.createCriteria(Entrada.class)
							.addOrder(Order.desc("data")).list());
			container.addNestedContainerProperty("usuario.nome");

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
