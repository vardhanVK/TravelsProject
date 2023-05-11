package com.dao;

import java.util.List;


public interface DAO<T> {
	
	public T get(int t);
	
	public T get(String t);
	
	public List<T> getAll();
	
	public List<T> getAll(String t);
	
	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);

	
}
