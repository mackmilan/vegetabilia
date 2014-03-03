package br.com.sapientia.vegetabilia.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import com.vaadin.data.util.BeanItemContainer;

import br.com.sapientia.vegetabilia.models.Produto;
import br.com.sapientia.vegetabilia.utils.DAOSample;

@SuppressWarnings("unchecked")
public class ProdutoDAO extends DAOSample {

	public boolean salvar(Produto p) {
		boolean r = false;

		try {
			this.start();

			this.sessao.save(p);

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

	public boolean editar(Produto p) {
		boolean r = false;

		try {
			this.start();

			this.sessao.update(p);

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

	public boolean excluir(Produto p) {
		boolean r = false;

		try {
			this.start();

			this.sessao.delete(p);

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

	public BeanItemContainer<Produto> listar() {
		BeanItemContainer<Produto> container = null;

		try {
			this.start();

			container = new BeanItemContainer<Produto>(Produto.class,
					this.sessao.createCriteria(Produto.class)
							.addOrder(Order.asc("nome")).list());
			container.addNestedContainerProperty("medida.nome");
			container.addNestedContainerProperty("tipo.nome");

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

	public List<Produto> listarEstoque() {
		List<Produto> list = null;

		try {
			this.start();

			list = this.sessao.createCriteria(Produto.class)
					.addOrder(Order.asc("nome")).list();

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

		return list;
	}
}
