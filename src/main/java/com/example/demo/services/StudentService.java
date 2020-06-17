package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Agentur;
import com.example.demo.domain.Student;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.repositories.AgenturRepository;
import com.example.demo.repositories.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AgenturRepository agenturRepository;
	
	
	public Iterable<Student> findAllStudents(){
		
		return studentRepository.findAll();		
	}

	
	public Student findStudentByID(long student_id) {
		
		Student theStudent = studentRepository.findStudentByID(student_id);
				
			if(theStudent == null) {	
				throw new StudentNotFoundException("Der Student ID: '"+ student_id + "'is nicht vorhanden.");
			
			}
		
		return theStudent;	
	}
	
	public Iterable<Student> findStudentByAgenturID(long agentur_id) {
		
		Iterable<Student> theStudent = studentRepository.findStudentByAgenturID(agentur_id);
				
			if(theStudent == null) {	
				throw new StudentNotFoundException("Der Student mit der Agentur: '"+ agentur_id + "'is nicht vorhanden.");
			
			}
		
		return theStudent;
	}
	
	
	public Student saveOrUpdateStudent(Student student, long agentur_id) {
		
	try {	
		
		if(student.getStudent_index() == null) {
			
			Agentur theagentur = agenturRepository.findAgenturByID(agentur_id);
			if( theagentur!= null) {
				
				theagentur.addStudent(student);
			}
			student.setStudent_sortierung(student.getStudent_nachname()+", "+student.getStudent_vorname());
			return studentRepository.save(student);
			
		}else { 
			
				Student theStudent = findStudentByID(student.getStudent_index());
				if(theStudent == null) {	
					throw new StudentNotFoundException("Der Student ID: '"+ student.getStudent_index() + "'is nicht vorhanden.");
			}
			
				Agentur theagentur = agenturRepository.findAgenturByID(agentur_id);
				theagentur.addStudent(student);
				student.setStudent_sortierung(student.getStudent_nachname()+", "+student.getStudent_vorname());
				return studentRepository.save(student);	
		}	
				
		}catch (StudentNotFoundException e){			
			throw e;
		}catch (Exception e){			
			throw new StudentNotFoundException("Der Student ID: '"+ student.getStudent_index() + "' oder Die Agentur ID: '" + agentur_id + "' is nicht vorhanden.");
		}

	}
	
	
	public void deleteStudentById(long student_id) {
		
		Student theStudent = studentRepository.findStudentByID(student_id);
		
		if(theStudent == null) {
			
			throw new StudentNotFoundException("Der Student ID: '"+ student_id + "'is nicht vorhanden.");
		}

		studentRepository.delete(theStudent);
	}
	
}