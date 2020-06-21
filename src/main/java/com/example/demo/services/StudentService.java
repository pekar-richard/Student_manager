package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Agentur;
import com.example.demo.domain.Student;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.repositories.AgenturRepository;
import com.example.demo.repositories.StudentRepository;
import org.springframework.data.domain.Sort;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AgenturRepository agenturRepository;
	
	
	public Iterable<Student> findAllStudents(Sort sort){
		
		return studentRepository.findAll(sort);		
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
	
	
	public Student saveOrUpdateStudent(Student student) {
		
	try {	
		
			Agentur theagentur = student.getAgentur();
			
			Agentur theagenturFromDb = null;
			
			if (theagentur != null) {
				theagenturFromDb = agenturRepository.findAgenturByID(theagentur.getAgenturIndex());
			}
						
			if( theagenturFromDb!= null) {
				theagenturFromDb.addStudent(student);
			}
		
			if(student.getStudentIndex() != null) {
				Student theStudent = findStudentByID(student.getStudentIndex());
				if(theStudent == null) {	
					throw new StudentNotFoundException("Der Student ID: '"+ student.getStudentIndex() + "'is nicht vorhanden.");
				}
			}	
			
			student.setStudentSortierung(student.getStudentNachname()+", "+student.getStudentVorname());
			return studentRepository.save(student);
				
		} catch (StudentNotFoundException e){			
			throw e;
		} catch (Exception e){			
			throw new StudentNotFoundException("Der Student ID: '"+ student.getStudentIndex() + "' oder Die Agentur ID is nicht vorhanden.");
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