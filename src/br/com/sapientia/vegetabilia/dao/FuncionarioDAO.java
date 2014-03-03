package br.com.sapientia.vegetabilia.dao;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import com.vaadin.data.util.BeanItemContainer;

import br.com.sapientia.vegetabilia.models.Funcionario;
import br.com.sapientia.vegetabilia.utils.DAOSample;

@SuppressWarnings("unchecked")
public class FuncionarioDAO extends DAOSample {

	public boolean salvar(Funcionario f) {
		boolean r = false;

		try {
			this.start();

			this.sessao.save(f);

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

	public boolean editar(Funcionario f) {
		boolean r = false;

		try {
			this.start();

			this.sessao.update(f);

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

	public boolean excluir(Funcionario f) {
		boolean r = false;

		try {
			this.start();

			this.sessao.delete(f);

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

	public BeanItemContainer<Funcionario> listar() {
		BeanItemContainer<Funcionario> container = null;

		try {
			this.start();

			container = new BeanItemContainer<Funcionario>(Funcionario.class,
					this.sessao.createCriteria(Funcionario.class)
							.addOrder(Order.asc("nome")).list());
			container.addNestedContainerProperty("cidade.nome");
			container.addNestedContainerProperty("cidade.estado.nome");

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
