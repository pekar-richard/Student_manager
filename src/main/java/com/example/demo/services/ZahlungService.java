package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Agentur;
import com.example.demo.domain.Lektion;
import com.example.demo.domain.Student;
import com.example.demo.domain.Zahlung;
import com.example.demo.exceptions.LektionNotFoundException;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.exceptions.ZahlungNotFoundException;
import com.example.demo.repositories.AgenturRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.ZahlungRepository;

@Service
public class ZahlungService {
	
	@Autowired
	private ZahlungRepository zahlungRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AgenturRepository agenturRepository;
	
	
	public Iterable<Zahlung> findAllZahlungs(){
		
		return zahlungRepository.findAll();		
		
	}
	
	public List<Zahlung> findZahlungByStudentAndAgentur(long student_index, long agentue_index){
		
		return zahlungRepository.findZahlungByStudentAndAgentur(student_index, agentue_index);		
	}
	
	public Zahlung findZahlungByID(long zahlung_id) {
	
	Zahlung theZahlung = zahlungRepository.findZahlungByID(zahlung_id);
			
		if(theZahlung == null) {	
			throw new ZahlungNotFoundException("Die Zahlung ID:'"+ zahlung_id + "'ist nicht vorhanden.");
		}
	
	return theZahlung;	
}
	
	public Zahlung saveOrUpdateZahlung(Zahlung zahlung) {	
	
	try {	
		
		boolean isInsert = zahlung.getZahlungIndex() == null;
		boolean isUpdate = !isInsert;
		
		Lektion theLektion = zahlung.getLektion();
		Student theStudent = zahlung.getStudent();
		Agentur theAgentur = zahlung.getAgentur();
		
		Lektion theLektionFromDb = null;
		Agentur theAgenturFromDb = null;
		Student theStudentFromDb = null;
		List<Zahlung> thezalungsFromDb = null;
		
		double zahlungBetragSum = 0;
		
		if (theLektion == null && theStudent == null) {

			throw new ZahlungNotFoundException("Der Student und Die Lektion ist nicht vorhanden.");
		}
		
		zahlung.setLektion(null);
		
		if (theStudent != null) {
			theStudentFromDb = studentRepository.findStudentByID(theStudent.getStudentIndex());
		}

		if (theStudentFromDb != null) {

			zahlung.setStudent(theStudentFromDb);
		}
		
		if (theAgentur != null) {
			theAgenturFromDb = agenturRepository.findAgenturByID(theAgentur.getAgenturIndex());
		}

		if (theAgenturFromDb != null) {
			zahlung.setAgentur(theAgenturFromDb);
		}
		
		if (isUpdate) {
			Zahlung theZahlung = zahlungRepository.findZahlungByID(zahlung.getZahlungIndex());
			if (theZahlung == null) {
				throw new ZahlungNotFoundException(
						"Die Zahlung ID: '" + zahlung.getZahlungIndex() + "'ist nicht vorhanden.");
			}
		}
		
		zahlungRepository.save(zahlung);
		
		
		if (theStudentFromDb != null) {
			
			
			if (isUpdate) {

				  Zahlung thezahlungFromDb = zahlungRepository.findZahlungByID(zahlung.getZahlungIndex());
				  
				  if(thezahlungFromDb != null && (thezahlungFromDb.getZahlungBetrag() != zahlung.getZahlungBetrag())) {
						 thezahlungFromDb.setZahlungBetrag(zahlung.getZahlungBetrag());	
						 zahlungRepository.save(thezahlungFromDb);		  
				  }

			}
					
			thezalungsFromDb = zahlungRepository.findZahlungByStudentID(theStudentFromDb.getStudentIndex());
			if (thezalungsFromDb != null) {
				for (Zahlung var : thezalungsFromDb) {
					zahlungBetragSum = zahlungBetragSum + var.getZahlungBetrag();
				}
			}
			
			theStudentFromDb.setStudentKredit(zahlungBetragSum);
			
			studentRepository.save(theStudentFromDb);
						
	
		}
		return zahlung;
		
	}catch (ZahlungNotFoundException e){			
		throw e;
		}catch (Exception e){			
		throw  new ZahlungNotFoundException("Die Zahlung ID: '"+ zahlung.getZahlungIndex() + "' oder Der Student ID: '"+ zahlung.getStudent().getStudentIndex().shortValue() + "' ist nicht vorhanden.");
	}

	}
	
	public void deleteZahlungById(long zahlung_id) {
		
		List<Zahlung> thezalungsFromDb = null;
		double zahlungBetragSum = 0;
		
		
		Zahlung theZahlung = zahlungRepository.findZahlungByID(zahlung_id);
	
	if(theZahlung == null) {
		
		throw new ZahlungNotFoundException("Die Zahlung ID:'"+ zahlung_id + "'ist nicht vorhanden.");
	}
	
	Student theStudent = theZahlung.getStudent();
	theZahlung.setZahlungBetrag(0);
	zahlungRepository.save(theZahlung);
	
	thezalungsFromDb = zahlungRepository.findZahlungByStudentID(theStudent.getStudentIndex());
	if (thezalungsFromDb != null) {
		for (Zahlung var : thezalungsFromDb) {
			zahlungBetragSum = zahlungBetragSum + var.getZahlungBetrag();
		}
	}
	
	theZahlung.getStudent().setStudentKredit(zahlungBetragSum);
	theZahlung.removeStudent();
	theZahlung.removeAgentur();
	zahlungRepository.delete(theZahlung);

}
	public List<Zahlung> findZahlungByStudentID(long student_id){
		
		return zahlungRepository.findZahlungByStudentID(student_id);
		
	}

}
