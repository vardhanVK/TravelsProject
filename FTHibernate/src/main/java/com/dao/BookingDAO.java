package com.dao;

import java.util.List;

import org.hibernate.Session;

import com.entity.Booking;
import com.utils.HiberanteUtils;

public class BookingDAO implements DAO<Booking>{
	
	private static BookingDAO bookingDaoObject = new BookingDAO();
	
	public static BookingDAO getBookingDaoObject() {
		return bookingDaoObject;
	}
	
	Session session = HiberanteUtils.getSessionFactory().openSession();
	
	@Override
	public Booking get(int bookingId) {
		return session.get(Booking.class, bookingId);
	}

	@Override
	public Booking get(String email) {
		return (Booking) session.createQuery("FROM Booking where email = ?1",Booking.class).setParameter(1, email);
	}

	@Override
	public List<Booking> getAll() {
		return session.createQuery("FROM Booking",Booking.class).list();
	}
	
	@Override
	public List<Booking> getAll(String email) {
		return session.createQuery("FROM Booking where email = ?1",Booking.class).setParameter(1, email).list();
	}

	@Override
	public void save(Booking booking) {
		session.beginTransaction();
		session.persist(booking);
		session.getTransaction().commit();
	}

	@Override
	public void update(Booking booking) {
		session.beginTransaction();
		session.merge(booking);
		session.getTransaction().commit();
	}

	@Override
	public void delete(Booking booking) {
		session.beginTransaction();
		session.remove(booking);
		session.getTransaction().commit();
	}

}
