package com.multipleDB.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multipleDB.demo.dao.Departments;
import com.multipleDB.demo.repository.DepartmentsRepository;

@RestController
@RequestMapping("/dbOne")
public class DBOneController {
	
	@Autowired
	DepartmentsRepository departmentsRepository;
	
	@GetMapping("/getAllDepartments")
	public List<Departments> getAllDepartments() {
		return departmentsRepository.getAllDepartments();
	}
	
	@GetMapping("/findDepartmentById")
	public Departments findDepartmentById(@RequestParam("id")Integer id) {
		return departmentsRepository.findDepartmentById(id);
	}
}

