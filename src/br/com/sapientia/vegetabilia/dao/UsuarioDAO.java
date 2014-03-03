package br.com.sapientia.vegetabilia.dao;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import com.vaadin.data.util.BeanItemContainer;

import br.com.sapientia.vegetabilia.models.Usuario;
import br.com.sapientia.vegetabilia.utils.DAOSample;

@SuppressWarnings("unchecked")
public class UsuarioDAO extends DAOSample {

	public boolean salvar(Usuario u) {
		boolean r = false;

		try {
			this.start();

			this.sessao.save(u);

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

	public boolean editar(Usuario u) {
		boolean r = false;

		try {
			this.start();

			this.sessao.update(u);

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

	public boolean excluir(Usuario u) {
		boolean r = false;

		try {
			this.start();

			this.sessao.delete(u);

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

	public BeanItemContainer<Usuario> listar() {
		BeanItemContainer<Usuario> container = null;

		try {
			this.start();

			container = new BeanItemContainer<Usuario>(Usuario.class,
					this.sessao.createCriteria(Usuario.class)
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

	public Usuario obter(Integer cod) {
		Usuario u = null;

		try {
			this.start();
			this.hql = "from Usuario where codUsuario = :cod";
			this.consulta = this.sessao.createQuery(hql);
			this.consulta.setInteger("cod", cod);

			u = (Usuario) this.consulta.uniqueResult();

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

		return u;
	}
}
