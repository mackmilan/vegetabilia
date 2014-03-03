package br.com.sapientia.vegetabilia.dao;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import com.vaadin.data.util.BeanItemContainer;

import br.com.sapientia.vegetabilia.models.TipoProduto;
import br.com.sapientia.vegetabilia.utils.DAOSample;

@SuppressWarnings("unchecked")
public class TipoProdutoDAO extends DAOSample {

	public boolean salvar(TipoProduto t) {
		boolean r = false;

		try {
			this.start();

			this.sessao.save(t);

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

	public boolean editar(TipoProduto t) {
		boolean r = false;

		try {
			this.start();

			this.sessao.update(t);

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

	public boolean excluir(TipoProduto t) {
		boolean r = false;

		try {
			this.start();

			this.sessao.delete(t);

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

	public BeanItemContainer<TipoProduto> listar() {
		BeanItemContainer<TipoProduto> container = null;

		try {
			this.start();

			container = new BeanItemContainer<TipoProduto>(TipoProduto.class,
					this.sessao.createCriteria(TipoProduto.class)
							.addOrder(Order.asc("nome")).list());

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
