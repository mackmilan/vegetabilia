package br.com.sapientia.vegetabilia.utils;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAOSample {
	public Session sessao = null;
	public Transaction trans = null;
	public Query consulta = null;
	public String hql = null;

	public void start() {
		sessao = HibernateUtil.getSessionFactory().openSession();
		trans = sessao.beginTransaction();
	}

	public void closeSession() {
		if (this.sessao.isOpen()) {
			this.sessao.close();
		}
	}

	public void rollbackTrans(HibernateException e) {
		if (this.trans.isActive()) {
			trans.rollback();
		}

		System.err
				.println("Erro ao realizar operação. Erro: " + e.getMessage());
	}

	public void commit() {
		this.trans.commit();
	}

	public void sessionException(Throwable e) {
		System.err.println("Erro ao fechar sessão. Erro: " + e.getMessage());
	}
}
