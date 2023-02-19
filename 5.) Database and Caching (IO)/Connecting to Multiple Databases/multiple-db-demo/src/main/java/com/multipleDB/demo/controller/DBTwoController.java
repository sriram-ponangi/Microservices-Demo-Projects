package com.multipleDB.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multipleDB.demo.dao.Students;
import com.multipleDB.demo.repository.StudentsRepository;

@RestController
@RequestMapping("/dbTwo")
public class DBTwoController {

	@Autowired
	StudentsRepository studentsRepository;
	
	@GetMapping("/getAllStudents")
	public List<Students> getAllStudents() {		
		return studentsRepository.getAllStudents();
	}
	
	@GetMapping("/findStudentById")
	public Students findStudentById(@RequestParam("id")String id) {
		return studentsRepository.findStudentById(id);
	}
}