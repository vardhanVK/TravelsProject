package com.dao;

import java.util.List;

import org.hibernate.Session;

import com.entity.Logo;
import com.utils.HiberanteUtils;

public class FilesDAO implements DAO<Logo> {

	private static FilesDAO filesDao = new FilesDAO();

	public static FilesDAO getFilesDaoObject() {
		return filesDao;
	}

	Session session = HiberanteUtils.getSessionFactory().openSession();
	
	@Override
	public Logo get(int id) {
		return session.get(Logo.class, id);
		
	}

	@Override
	public Logo get(String id) {
		return session.get(Logo.class, id);
	}

	@Override
	public List<Logo> getAll() {
		return session.createQuery("From Logo", Logo.class).list();
	}
	
	@Override
	public List<Logo> getAll(String id) {
		return session.createQuery("From Logo", Logo.class).list();
	}

	@Override
	public void save(Logo file) {
		session.beginTransaction();
		session.persist(file);
		session.getTransaction().commit();
	}

	@Override
	public void update(Logo file) {
		session.beginTransaction();
		session.merge(file);
		session.getTransaction().commit();
	}

	@Override
	public void delete(Logo file) {
		session.beginTransaction();
		session.remove(file);
		session.getTransaction().commit();
	}

}
