package com.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.Booking;
import com.entity.Bus;
import com.entity.Logo;
import com.entity.Passenger;
import com.entity.UserAccount;

public class HiberanteUtils {
	
	private HiberanteUtils() {
	}

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {
				Configuration config = new Configuration();
				config.configure();
				config.addAnnotatedClass(UserAccount.class);
				config.addAnnotatedClass(Booking.class);
				config.addAnnotatedClass(Passenger.class);
				config.addAnnotatedClass(Bus.class);
				config.addAnnotatedClass(Logo.class);
				sessionFactory = config.buildSessionFactory();
				return sessionFactory;
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		return sessionFactory;
	}
	
}
