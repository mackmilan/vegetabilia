package br.com.sapientia.vegetabilia.dao;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import com.vaadin.data.util.BeanItemContainer;

import br.com.sapientia.vegetabilia.models.Saida;
import br.com.sapientia.vegetabilia.utils.DAOSample;

@SuppressWarnings("unchecked")
public class SaidaDAO extends DAOSample {

	public boolean salvar(Saida s) {
		boolean r = false;

		try {
			this.start();

			this.sessao.save(s);

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

	public boolean editar(Saida s) {
		boolean r = false;

		try {
			this.start();

			this.sessao.update(s);

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

	public boolean excluir(Saida s) {
		boolean r = false;

		try {
			this.start();

			this.sessao.delete(s);

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

	public BeanItemContainer<Saida> listar() {
		BeanItemContainer<Saida> container = null;

		try {
			this.start();

			container = new BeanItemContainer<Saida>(Saida.class, this.sessao
					.createCriteria(Saida.class).addOrder(Order.desc("data"))
					.list());
			container.addNestedContainerProperty("cliente.nome");
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
