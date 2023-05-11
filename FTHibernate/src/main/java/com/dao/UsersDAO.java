package com.dao;

import java.util.List;

import org.hibernate.Session;

import com.entity.UserAccount;
import com.utils.HiberanteUtils;

public class UsersDAO implements DAO<UserAccount> {

	private static UsersDAO usersDaoObject = new UsersDAO();

	public static UsersDAO getUsersDaoObject() {
		return usersDaoObject;
	}

	Session session = HiberanteUtils.getSessionFactory().openSession();

	@Override
	public UserAccount get(int id) {
		return session.get(UserAccount.class, id);
	}

	@Override
	public UserAccount get(String email) {
		return session.get(UserAccount.class, email);
	}

	@Override
	public List<UserAccount> getAll() {
		return session.createQuery("From UserAccount", UserAccount.class).list();
	}
	
	@Override
	public List<UserAccount> getAll(String email) {
		return session.createQuery("From UserAccount", UserAccount.class).list();
	}

	@Override
	public void save(UserAccount user) {
		session.beginTransaction();
		session.persist(user);
		session.getTransaction().commit();
	}

	@Override
	public void update(UserAccount user) {
		session.beginTransaction();
		session.merge(user);
		session.getTransaction().commit();
	}

	@Override
	public void delete(UserAccount user) {
		session.beginTransaction();
		session.remove(user);
		session.getTransaction().commit();
	}

}
