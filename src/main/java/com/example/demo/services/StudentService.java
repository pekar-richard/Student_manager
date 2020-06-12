package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Agentur;
import com.example.demo.domain.Student;
import com.example.demo.exceptions.AgenturNotFoundException;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.repositories.AgenturRepository;
import com.example.demo.repositories.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AgenturRepository agenturRepository;
	
	public Student saveOrUpdateStudent(Student student, long agentur_id) {
	try {	
		
		if(student.getStudent_index() == null) {
			
			studentRepository.save(student);
			
			Agentur theagentur = agenturRepository.findAgenturByID(agentur_id);
			if( theagentur!= null) {
				
				theagentur.addStudent(student);
				agenturRepository.save(theagentur);
			}
			
		}else {
			
				Student theStudent = findStudentByID(student.getStudent_index());
				if(theStudent == null) {	
					throw new StudentNotFoundException("Der Student ID: '"+ student.getStudent_index() + "'is nicht vorhanden.");
			}
			
				theStudent=student;
				studentRepository.save(theStudent);
				Agentur theagentur = agenturRepository.findAgenturByID(agentur_id);
				theagentur.addStudent(theStudent);
				agenturRepository.save(theagentur);				
		}	
		
		return student;
				
		}catch (StudentNotFoundException e){			
			throw e;
		}catch (Exception e){			
			throw new StudentNotFoundException("Der Student ID: '"+ student.getStudent_index() + "' oder Die Agentur ID: '" + agentur_id + "' is nicht vorhanden.");
		}

	}
	
	public Iterable<Student> findAllStudents(){
		
		return studentRepository.findAll();		
	}

	
	public Student findStudentByID(long student_id) {
		
		return studentRepository.findStudentByID(student_id);	
	}
}