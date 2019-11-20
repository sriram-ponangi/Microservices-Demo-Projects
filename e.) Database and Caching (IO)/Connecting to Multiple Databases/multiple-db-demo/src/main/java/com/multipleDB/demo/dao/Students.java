package com.multipleDB.demo.dao;

import java.io.Serializable;
import java.math.BigDecimal;


public class Students implements Serializable{	
	
	private static final long serialVersionUID = -5771608605411609246L;
	
	
	private BigDecimal student_id;	
	private String student_name;
	public BigDecimal getStudent_id() {
		return student_id;
	}
	public void setStudent_id(BigDecimal student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	
	
	
}
