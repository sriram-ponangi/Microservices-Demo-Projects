package com.multipleDB.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.multipleDB.demo.dao.Students;

@Repository
public class StudentsRepository {
	
	@Qualifier("JdbcTemplateTwo")
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Students> getAllStudents() {
		String sql = "select * from Students";
		List<Students> students = new ArrayList<Students>();
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<?, ?> row : rows) {
			Students student = new Students();
			student.setStudent_id((java.math.BigDecimal)(row.get("student_id")));
			student.setStudent_name((String)(row.get("student_name")));
			students.add(student);
		}		
		return students;
	}
	
	public Students findStudentById(String id) {
		String sql = "SELECT * FROM Students WHERE student_id = ?";
		/*
		 * NOTE: While using BeanPropertyRowMapper
		 * the column names should be the same as the field names of the class 
		 */
		Students student  = (Students)jdbcTemplate.queryForObject(
				sql, new Object[] { id }, 
				new BeanPropertyRowMapper<Students>(Students.class));
		
		return student;
	}
	
}
