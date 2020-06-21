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
	
	public Rechnung saveOrUpdateRechnung(Rechnung rechnung, long student_id, long agentur_id) {
	try {	
		
		
		Agentur theAgentur = agenturRepository.findAgenturByID(agentur_id);
		Student theStudent1 = studentRepository.findStudentByID(student_id);
		
		if(theAgentur == null && theStudent1 == null) {
			
			throw new RechnungNotFoundException("Der Student ID: '"+ student_id + "' oder Die Agentur ID: '" + agentur_id + "' is nicht vorhanden.");
		}
		
		if(rechnung.getRechnIndex() == null) {
		 
			
			if( theAgentur!= null) {
				
				theAgentur.addRechnung(rechnung);
			}
	
			
			if( theStudent1!= null) {
				
				theStudent1.addRechnung(rechnung);
			}
			
			return rechnungRepository.save(rechnung);
			
		}else {
				Rechnung theRechnung = rechnungRepository.findRechnungByID(rechnung.getRechnIndex());
				
				if(theRechnung == null  ) {	
					throw new RechnungNotFoundException("Die Rechnung ID: '"+ rechnung.getRechnIndex() + "'ist nicht vorhanden.");
			}
			
				Agentur theagentur = agenturRepository.findAgenturByID(agentur_id);
				if( theagentur!= null) {
					
					theagentur.addRechnung(rechnung);
				}
				
				Student theStudent = studentRepository.findStudentByID(student_id);
				if( theStudent!= null) {
					
					theStudent1.addRechnung(rechnung);
				}
				
			
			return rechnungRepository.save(rechnung);
				
		}	
				
		}catch (RechnungNotFoundException e){			
			throw e;
		}catch (Exception e){			
			throw new RechnungNotFoundException("Der Student ID: '"+ student_id + "' oder Die Agentur ID: '" + agentur_id + "' is nicht vorhanden.");
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
