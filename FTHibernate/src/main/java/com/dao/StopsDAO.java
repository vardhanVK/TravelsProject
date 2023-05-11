package com.dao;

import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;

import com.utils.HiberanteUtils;

public class StopsDAO {
	
	private StopsDAO() {
	}
	
	static Session session = HiberanteUtils.getSessionFactory().openSession();
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static Set<String> getStops(){
		TreeSet<String> stations = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		stations.addAll(session.createNativeQuery("SELECT station FROM stops").list());
		return stations;
	}
	
}
