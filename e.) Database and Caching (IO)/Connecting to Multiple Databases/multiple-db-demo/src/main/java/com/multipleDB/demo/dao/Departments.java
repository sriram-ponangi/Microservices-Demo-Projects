package com.multipleDB.demo.dao;

import java.io.Serializable;
import java.math.BigDecimal;

public class Departments  implements Serializable{
	
	private static final long serialVersionUID = -4461747530458253960L;
	
	
	private BigDecimal id;
	private String name;
	private String location;
	
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}	
}
