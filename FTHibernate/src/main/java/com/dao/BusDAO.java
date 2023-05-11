package com.dao;

import java.util.List;

import org.hibernate.Session;

import com.entity.Bus;
import com.utils.HiberanteUtils;

public class BusDAO implements DAO<Bus> {
	
	private static BusDAO busDaoObject = new BusDAO();
	
	public static BusDAO getBusDaoObject() {
		return busDaoObject;
	}

	Session session = HiberanteUtils.getSessionFactory().openSession();

	@Override
	public Bus get(int busNumber) {
		return session.get(Bus.class, busNumber);
	}

	@Override
	public Bus get(String busNumber) {
		return session.get(Bus.class, busNumber);
	}

	@Override
	public List<Bus> getAll() {
		return session.createQuery("FROM BUS", Bus.class).list();
	}

	@Override
	public List<Bus> getAll(String busNumber) {
		return session.createQuery("FROM BUS WHERE busNumber= ?1", Bus.class).setParameter(1, busNumber).list();
	}

	@Override
	public void save(Bus bus) {
		session.beginTransaction();
		session.persist(bus);
		session.getTransaction().commit();
	}

	@Override
	public void update(Bus bus) {
		session.beginTransaction();
		session.merge(bus);
		session.getTransaction().commit();
	}

	@Override
	public void delete(Bus bus) {
		session.beginTransaction();
		session.remove(bus);
		session.getTransaction().commit();
	}

}
