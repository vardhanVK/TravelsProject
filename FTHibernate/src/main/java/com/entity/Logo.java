package com.entity;

import java.sql.Clob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "files")
public class Logo {
	
	@Id
	private int id;
	
	@Column(name = "LOGO")
	private Clob file;
	
	public Logo() {
	}

	public Logo(int id, Clob file) {
		super();
		this.id = id;
		this.file = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Clob getLogo() {
		return file;
	}

	public void setLogo(Clob file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Logo [id=" + id + ", logo=" + file + "]";
	}
	
	

}
