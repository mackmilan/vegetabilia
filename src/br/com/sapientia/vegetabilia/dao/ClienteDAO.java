package br.com.sapientia.vegetabilia.dao;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import com.vaadin.data.util.BeanItemContainer;

import br.com.sapientia.vegetabilia.models.Cliente;
import br.com.sapientia.vegetabilia.utils.DAOSample;

@SuppressWarnings("unchecked")
public class ClienteDAO extends DAOSample {

	public boolean salvar(Cliente c) {
		boolean r = false;

		try {
			this.start();

			this.sessao.save(c);

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

	public boolean editar(Cliente c) {
		boolean r = false;

		try {
			this.start();

			this.sessao.update(c);

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

	public boolean excluir(Cliente c) {
		boolean r = false;

		try {
			this.start();

			this.sessao.delete(c);

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

	public BeanItemContainer<Cliente> listar() {
		BeanItemContainer<Cliente> container = null;

		try {
			this.start();

			container = new BeanItemContainer<Cliente>(Cliente.class,
					this.sessao.createCriteria(Cliente.class)
							.addOrder(Order.asc("nome")).list());
			container.addNestedContainerProperty("cidade.estado.nome");
			container.addNestedContainerProperty("cidade.nome");

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
