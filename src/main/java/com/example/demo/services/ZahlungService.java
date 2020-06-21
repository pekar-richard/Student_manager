package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Lektion;
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
			
		if(zahlung.getZahlungIndex() == null) {
			
			Student theStudent = studentRepository.findStudentByID(student_id);
			
			// Tu som zmazal zahlung Betragubrig!!!
			
			if( theStudent!= null) {
				
				theStudent.addZahlung(zahlung);
				theStudent.setStudentKredit(theStudent.getStudentKredit()+zahlung.getZahlungBetrag());
				
			}else {
				
				throw new StudentNotFoundException("Der Student ID: '"+ student_id + "'is nicht vorhanden.");	
			}
			
			return zahlungRepository.save(zahlung);
			
		}else {
		
			Student theStudent = studentRepository.findStudentByID(student_id);
			Zahlung theZahlung = zahlungRepository.findZahlungByID(zahlung.getZahlungIndex());
			
				if(theZahlung == null) {	
					throw new ZahlungNotFoundException("Die Zahlung ID:'"+ zahlung.getZahlungIndex() + "'ist nicht vorhanden.");
				}

				if(theZahlung.getZahlungBetrag() != theZahlung.getStudent().getStudentKredit()){
					
					double theZahlungBetrag = theZahlung.getZahlungBetrag();
					double theStudentKredit = theZahlung.getStudent().getStudentKredit();
					
					if(theZahlungBetrag<0) {
						theZahlung.getStudent().setStudentKredit(theStudentKredit+theZahlungBetrag);
			
					}
					
					// Tu som zmazal zahlung Betragubrig!!!
					
					theZahlung.getStudent().setStudentKredit(theStudentKredit-theZahlungBetrag);
					
				}
				theStudent.addZahlung(zahlung);
			return	zahlungRepository.save(zahlung);
			
		}	
			
	}catch (ZahlungNotFoundException e){			
		throw e;
		}catch (Exception e){			
		throw new ZahlungNotFoundException("Die Zahlung ID: '"+ zahlung.getZahlungIndex() + "' oder Der Student ID: '"+ student_id + "' ist nicht vorhanden.");
	}

	}
	
	public void deleteZahlungById(long zahlung_id) {
		
		
		Zahlung theZahlung = zahlungRepository.findZahlungByID(zahlung_id);
	
	
	if(theZahlung == null) {
		
		throw new ZahlungNotFoundException("Die Zahlung ID:'"+ zahlung_id + "'ist nicht vorhanden.");
	}
	
	double theZahlungBetrag = theZahlung.getZahlungBetrag();
	theZahlung.getStudent().setStudentKredit(theZahlung.getStudent().getStudentKredit()-theZahlungBetrag);
	
	theZahlung.removeStudent();
	zahlungRepository.delete(theZahlung);

}
	public List<Zahlung> findZahlungByStudentID(long student_id){
		
		return zahlungRepository.findZahlungByStudentID(student_id);
		
	}

}
