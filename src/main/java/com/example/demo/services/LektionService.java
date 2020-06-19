package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Iterable<Lektion> findAllLektions(){
		
		return lektionRepository.findAll();		
		
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
			theagenturFromDb = agenturRepository.findAgenturByID(theAgentur.getAgentur_index());
		}
		
		if( theagenturFromDb!= null) {
			theagenturFromDb.addLektion(lektion);
		}
		
		if (theStudent != null) {
			thestudentFromDb = studentRepository.findStudentByID(theStudent.getStudent_index());
		}
		
		if( thestudentFromDb!= null) {
			
			
			if(thestudentFromDb.getStudent_ersttermin()== null) {
				thestudentFromDb.setStudent_ersttermin(lektion.getLektion_datum());	
				
			}
			
			thestudentFromDb.setStudent_letztermin(lektion.getLektion_datum());
			thestudentFromDb.addLektion(lektion);
		}
				
		if(lektion.getLektion_index() != null) {
			Lektion theLektion = lektionRepository.findLektionByID(lektion.getLektion_index());
				if(theLektion == null) {	
						throw new LektionNotFoundException("Die Lektion ID: '"+ lektion.getLektion_index() + "'ist nicht vorhanden.");
					}
				}	
		
		return lektionRepository.save(lektion);
		
		} catch (LektionNotFoundException e){			
			throw e;
		} catch (Exception e){			
			throw new LektionNotFoundException("Der Student ID: '"+ lektion.getStudent().getStudent_index() + "' oder Die Agentur ID: '"+ lektion.getAgentur().getAgentur_index() + "' is nicht vorhanden.");
		}

	}
	
	public void deleteLektionById(long lektion_id) {
	
	Lektion theLektion = lektionRepository.findLektionByID(lektion_id);
	
	if(theLektion == null) {
		
		throw new LektionNotFoundException("Die Lektion ID: '"+ lektion_id + "'ist nicht vorhanden.");
	}

	theLektion.getStudent().setStudent_kredit(theLektion.getStudent().getStudent_kredit()+theLektion.getLektion_preis());
	theLektion.removeAgentur();
	theLektion.removeStudent(); 
	lektionRepository.delete(theLektion);
}
	
	public List<Lektion> findLektionByStudentID(long student_id){
		
		return lektionRepository.findLektionByStudentID(student_id);
		
	}
	
}
