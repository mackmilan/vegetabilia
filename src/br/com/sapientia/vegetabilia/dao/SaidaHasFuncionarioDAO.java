package br.com.sapientia.vegetabilia.dao;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import com.vaadin.data.util.BeanItemContainer;

import br.com.sapientia.vegetabilia.models.SaidaHasFuncionario;
import br.com.sapientia.vegetabilia.utils.DAOSample;

@SuppressWarnings("unchecked")
public class SaidaHasFuncionarioDAO extends DAOSample {

	public boolean salvar(SaidaHasFuncionario s) {
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

	public boolean editar(SaidaHasFuncionario s) {
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

	public boolean excluir(SaidaHasFuncionario s) {
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

	public BeanItemContainer<SaidaHasFuncionario> listar() {
		BeanItemContainer<SaidaHasFuncionario> container = null;

		try {
			this.start();

			container = new BeanItemContainer<SaidaHasFuncionario>(
					SaidaHasFuncionario.class, this.sessao
							.createCriteria(SaidaHasFuncionario.class)
							.addOrder(Order.desc("saida.data")).list());

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
