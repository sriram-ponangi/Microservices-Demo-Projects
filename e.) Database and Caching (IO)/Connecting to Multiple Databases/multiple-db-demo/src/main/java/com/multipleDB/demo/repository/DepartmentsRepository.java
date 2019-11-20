package com.multipleDB.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.multipleDB.demo.dao.Departments;

@Repository
public class DepartmentsRepository {
	
	@Qualifier("JdbcTemplateOne")
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Departments> getAllDepartments() {
		String sql = "select * from departments";
		List<Departments> departments = new ArrayList<Departments>();
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<?, ?> row : rows) {
			Departments department = new Departments();
			department.setId((java.math.BigDecimal)(row.get("department_id")));
			department.setLocation((String)(row.get("department_name")));
			department.setName((String)(row.get("location")));
			departments.add(department);
		}
		
		return departments;
	}
	
	public Departments findDepartmentById(Integer id) {
		String sql = "SELECT * FROM Departments WHERE department_id = ?";
		
		Departments department  = (Departments)jdbcTemplate.queryForObject(sql,new Object[] { id }, new RowMapper<Departments>() {
			@Override
			public Departments mapRow(ResultSet rs, int rowNum) throws SQLException {
				Departments department = new Departments();
				department.setId(rs.getBigDecimal("department_id"));
				department.setLocation(rs.getString("location"));
				department.setName(rs.getString("department_name"));
				return department;
			}
			
		});
		
		
		
		
		return department;
	}
	
}
