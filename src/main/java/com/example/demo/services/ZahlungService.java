package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Student;
import com.example.demo.domain.Zahlung;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.exceptions.ZahlungNotFoundException;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.ZahlungRepository;

@Service
public class ZahlungService {
	
	@Autowired
	private ZahlungRepository zahlungRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	public Iterable<Zahlung> findAllZahlungs(){
		
		return zahlungRepository.findAll();		
		
	}
	
	public Zahlung findZahlungByID(long zahlung_id) {
	
	Zahlung theZahlung = zahlungRepository.findZahlungByID(zahlung_id);
			
		if(theZahlung == null) {	
			throw new ZahlungNotFoundException("Die Zahlung ID:'"+ zahlung_id + "'ist nicht vorhanden.");
		}
	
	return theZahlung;	
}
	
	public Zahlung saveOrUpdateZahlung(Zahlung zahlung, long student_id) {	
	
	try {	
		
		Student theStudent = studentRepository.findStudentByID(student_id);
		if( theStudent!= null) {
			
			theStudent.addZahlung(zahlung);
			theStudent.setStudent_kredit(theStudent.getStudent_kredit()+zahlung.getZahlung_betrag());
		}else {
			
			throw new StudentNotFoundException("Der Student ID: '"+ student_id + "'is nicht vorhanden.");
			
		}
				
		if(zahlung.getZahlung_index() == null) {
			
			return zahlungRepository.save(zahlung);
			
		}else {
		
			Zahlung theZahlung = zahlungRepository.findZahlungByID(zahlung.getZahlung_index());
				if(theZahlung == null) {	
					throw new ZahlungNotFoundException("Die Zahlung ID:'"+ zahlung.getZahlung_index() + "'ist nicht vorhanden.");
				}

				if(theZahlung.getZahlung_betrag() != theZahlung.getStudent().getStudent_kredit()){
					
					double theZahlungBetrag = theZahlung.getZahlung_betrag();
					double theStudentKredit = theZahlung.getStudent().getStudent_kredit();
					
					if(theZahlungBetrag<0) {
						theZahlung.getStudent().setStudent_kredit(theStudentKredit+theZahlungBetrag);
					}
					
					if(theZahlungBetrag>0) {
						theZahlung.getStudent().setStudent_kredit(theStudentKredit-theZahlungBetrag);			
					}
					
					theZahlung.getStudent().setStudent_kredit(theStudentKredit-theZahlungBetrag);
					
				}
				
			return	zahlungRepository.save(zahlung);
			
		}	
			
	}catch (ZahlungNotFoundException e){			
		throw e;
		}catch (Exception e){			
		throw new ZahlungNotFoundException("Die Zahlung ID: '"+ zahlung.getZahlung_index() + "' oder Der Student ID: '"+ student_id + "' ist nicht vorhanden.");
	}

	}
	
	public void deleteZahlungById(long zahlung_id) {
		
		
		Zahlung theZahlung = zahlungRepository.findZahlungByID(zahlung_id);
	
	
	if(theZahlung == null) {
		
		throw new ZahlungNotFoundException("Die Zahlung ID:'"+ zahlung_id + "'ist nicht vorhanden.");
	}
	
	double theZahlungBetrag = theZahlung.getZahlung_betrag();
	theZahlung.getStudent().setStudent_kredit(theZahlung.getStudent().getStudent_kredit()-theZahlungBetrag);
	
	theZahlung.removeStudent();
	zahlungRepository.delete(theZahlung);

}
	

}
