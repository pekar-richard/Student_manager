package com.example.demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Student;
import com.example.demo.services.MapValidationErrorService;
import com.example.demo.services.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin //Important to get acces from react app to server
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@GetMapping("/allstudents")
	public Iterable<Student> getAllStudents(){return studentService.findAllStudents();}
	
	@PutMapping("/{agentur_id}")
	public ResponseEntity<?> createNewStudent(@Valid @RequestBody Student student, BindingResult result, @PathVariable long agentur_id ){			

	ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
	if(errorMap!=null) return errorMap;
	
	Student thestudent= studentService.saveOrUpdateStudent(student, agentur_id);
		
	return new ResponseEntity<Student>(thestudent, HttpStatus.CREATED);	
	}
	
	@GetMapping("/{student_id}")
	public ResponseEntity<?> findStudentByID(@PathVariable long student_id){			

	Student thestudent= studentService.findStudentByID(student_id);
		
	return new ResponseEntity<Student>(thestudent, HttpStatus.OK);	
	}
	
	
	@DeleteMapping("/{student_id}")	
	public ResponseEntity<?> deleteStudent(@PathVariable long student_id ){
		
		studentService.deleteStudentById(student_id);
		
		return new ResponseEntity<String>("Student with ID:'" + student_id + "' was deleted", HttpStatus.OK);
		
	}
	
}