package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Agentur;
import com.example.demo.domain.Rechnung;
import com.example.demo.domain.Student;
import com.example.demo.exceptions.RechnungNotFoundException;
import com.example.demo.repositories.AgenturRepository;
import com.example.demo.repositories.RechnungRepository;
import com.example.demo.repositories.StudentRepository;

@Service
public class RechnungService {
	
	@Autowired
	private RechnungRepository rechnungRepository;
	
	@Autowired
	private AgenturRepository agenturRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Iterable<Rechnung> findAllRechnungs(){
		
		return rechnungRepository.findAll();			
	}
	
	public Rechnung findRechnungByID(long rechnung_id) {
		
		Rechnung theRechnung = rechnungRepository.findRechnungByID(rechnung_id);
			
		if(theRechnung == null) {	
			throw new RechnungNotFoundException("Die Rechnung ID:'"+ rechnung_id + "'ist nicht vorhanden.");
		}
	
		return theRechnung;
	}
	
	public Rechnung saveOrUpdateRechnung(Rechnung rechnung) {
		
	try {	
		
		Student theStudent = rechnung.getStudent();
		Agentur theAgentur = rechnung.getAgentur();
		Agentur theAgenturFromDb = null;
		Student theStudentFromDb = null;
		
		if(theAgentur == null && theStudent == null) {
			
			throw new RechnungNotFoundException("Der Student ID: '"+ theStudent.getStudentIndex() + "' oder Die Agentur ID: '" + theAgentur.getAgenturIndex() + "' is nicht vorhanden.");
		}
		
		if (theStudent != null) {
			theStudentFromDb = studentRepository.findStudentByID(theStudent.getStudentIndex());
		}

		if (theStudentFromDb != null) {

			rechnung.setStudent(theStudentFromDb);
		}
			
		if (theAgentur != null) {
			theAgenturFromDb = agenturRepository.findAgenturByID(theAgentur.getAgenturIndex());
		}

		if (theAgenturFromDb != null) {
			rechnung.setAgentur(theAgenturFromDb);
		}
		
		
		return rechnungRepository.save(rechnung);
		
				
		}catch (RechnungNotFoundException e){			
			throw e;
		}catch (Exception e){			
			throw new RechnungNotFoundException("Der Student ID: '"+ rechnung.getStudent().getStudentIndex()  + "' oder Die Agentur ID: '" + rechnung.getAgentur().getAgenturIndex() + "' is nicht vorhanden.");
		}

	}
	
	public void deleteRechnungById(long rechnung_id) {
		
		
	Rechnung theRechnung = rechnungRepository.findRechnungByID(rechnung_id);
	
	if(theRechnung == null) {
		
		throw new RechnungNotFoundException("Die Rechnung ID: '"+ rechnung_id + "'ist nicht vorhanden.");
	}

	theRechnung.removeAgentur();
	theRechnung.removeStudent(); 
	rechnungRepository.delete(theRechnung);

}
	
}
