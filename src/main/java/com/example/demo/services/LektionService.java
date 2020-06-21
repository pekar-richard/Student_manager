package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Agentur;
import com.example.demo.domain.Lektion;
import com.example.demo.domain.Student;
import com.example.demo.exceptions.LektionNotFoundException;
import com.example.demo.repositories.AgenturRepository;
import com.example.demo.repositories.LektionRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.ZahlungRepository;


@Service
public class LektionService {
	
	@Autowired
	private LektionRepository lektionRepository;
	
	@Autowired
	private AgenturRepository agenturRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ZahlungRepository zahlungRepository;
	
	public Iterable<Lektion> findAllLektions(Sort sort){
		
		return lektionRepository.findAll(sort);		
		
	}
	
	public Lektion findLektionByID(long lektion_id) {
	
	Lektion theLektion = lektionRepository.findLektionByID(lektion_id);
			
		if(theLektion == null) {	
			throw new LektionNotFoundException("Die Lektion ID:'"+ lektion_id + "'ist nicht vorhanden.");
		}
	
	return theLektion;	
}
	
	public Lektion saveOrUpdateLektion(Lektion lektion) {
		
	try {	
		
		Agentur theAgentur = lektion.getAgentur();
		Student theStudent = lektion.getStudent();
		
		Agentur theagenturFromDb = null;
		Student thestudentFromDb = null;
		
		if(theAgentur == null && theStudent == null) {
			
			throw new LektionNotFoundException("Der Student und Die Agentur ist nicht vorhanden.");
		}
		
		if (theAgentur != null) {
			theagenturFromDb = agenturRepository.findAgenturByID(theAgentur.getAgenturIndex());
		}
		
		if( theagenturFromDb!= null) {
			theagenturFromDb.addLektion(lektion);
		}
		
		if (theStudent != null) {
			thestudentFromDb = studentRepository.findStudentByID(theStudent.getStudentIndex());
		}
		
		if( thestudentFromDb!= null) {
			
			
			if(thestudentFromDb.getStudentErsttermin()== null) {
				thestudentFromDb.setStudentErsttermin(lektion.getLektionDatum());	
				
			}
			
			thestudentFromDb.setStudentLetztermin(lektion.getLektionDatum());
			thestudentFromDb.addLektion(lektion);
		}
				
		if(lektion.getLektionIndex() != null) {
			Lektion theLektion = lektionRepository.findLektionByID(lektion.getLektionIndex());
				if(theLektion == null) {	
						throw new LektionNotFoundException("Die Lektion ID: '"+ lektion.getLektionIndex() + "'ist nicht vorhanden.");
					}
				}	
		
		return lektionRepository.save(lektion);
		
		} catch (LektionNotFoundException e){			
			throw e;
		} catch (Exception e){			
			throw new LektionNotFoundException("Der Student ID: '"+ lektion.getStudent().getStudentIndex() + "' oder Die Agentur ID: '"+ lektion.getAgentur().getAgenturIndex() + "' is nicht vorhanden.");
		}

	}
	
	public void deleteLektionById(long lektion_id) {
	
	Lektion theLektion = lektionRepository.findLektionByID(lektion_id);
	
	if(theLektion == null) {
		
		throw new LektionNotFoundException("Die Lektion ID: '"+ lektion_id + "'ist nicht vorhanden.");
	}

	theLektion.getStudent().setStudentKredit(theLektion.getStudent().getStudentKredit()+theLektion.getLektionPreis());
	theLektion.removeAgentur();
	theLektion.removeStudent(); 
	lektionRepository.delete(theLektion);
}
	
	public List<Lektion> findLektionByStudentID(long student_id){
		
		return lektionRepository.findLektionByStudentID(student_id);
		
	}
	
}
