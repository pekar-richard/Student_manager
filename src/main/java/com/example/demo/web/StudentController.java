package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Student;
import com.example.demo.services.StudentService;


@RestController
@RequestMapping("/api/project")
@CrossOrigin //Important to get acces from react app to server
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/allstudents")
	public Iterable<Student> getAllStudents(){return studentService.findAllStudents();}

}