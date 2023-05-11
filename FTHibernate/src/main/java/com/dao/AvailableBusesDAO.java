package com.dao;

import java.util.List;

import org.hibernate.Session;

import com.utils.HiberanteUtils;

public class AvailableBusesDAO {
	
	private AvailableBusesDAO() {
	}

	static Session session = HiberanteUtils.getSessionFactory().openSession();

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static List<Object[]> getBuses(String boardingStation, String destinationStation) {

		return session.createNativeQuery("SELECT * FROM "
				+ "(SELECT a.busNumber , a.station AS src, a.distance AS start, b.station AS dest, b.distance AS end "
				+ "FROM (SELECT * FROM stops WHERE station = ?) as a "
				+ "INNER JOIN (SELECT * FROM stops WHERE station = ?) as b " + "ON a.busNumber = b.busNumber) AS buses "
				+ "WHERE end - start >=0").setParameter(1, boardingStation).setParameter(2, destinationStation).list();

	}

}
