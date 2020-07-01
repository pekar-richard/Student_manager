package com.example.demo.services;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Agentur;
import com.example.demo.domain.Lektion;
import com.example.demo.domain.Student;
import com.example.demo.domain.Zahlung;
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

		boolean isInsert = lektion.getLektionIndex() == null;
		boolean isUpdate = !isInsert;
			
		Agentur theAgentur = lektion.getAgentur();
		Student theStudent = lektion.getStudent();
		
		Agentur theAgenturFromDb = null;
		Student theStudentFromDb = null;
		List<Zahlung> thezalungsFromDb = null;
		double zahlungBetragSum = 0;

		if (theAgentur == null && theStudent == null) {

			throw new LektionNotFoundException("Der Student und Die Agentur ist nicht vorhanden.");
		}

		if (theAgentur != null) {
			theAgenturFromDb = agenturRepository.findAgenturByID(theAgentur.getAgenturIndex());
		}

		if (theAgenturFromDb != null) {
			lektion.setAgentur(theAgenturFromDb);
		}

		if (theStudent != null) {
			theStudentFromDb = studentRepository.findStudentByID(theStudent.getStudentIndex());
		}

		if (theStudentFromDb != null) {

			lektion.setStudent(theStudentFromDb);
		}
			
		
		if (isUpdate) {
			Lektion theLektion = lektionRepository.findLektionByID(lektion.getLektionIndex());
			if (theLektion == null) {
				throw new LektionNotFoundException(
						"Die Lektion ID: '" + lektion.getLektionIndex() + "'ist nicht vorhanden.");
			}else {
				
				lektion.setZahlung(theLektion.getZahlung());
			}
		}
		
		
		lektionRepository.save(lektion);
		
		if (theStudentFromDb != null) {
			
			
			if (isUpdate) {

				  Zahlung thezahlungFromDb = zahlungRepository.findZahlungByID(lektion.getZahlung().getZahlungIndex());
				  if(thezahlungFromDb != null && (thezahlungFromDb.getZahlungBetrag() != lektion.getLektionPreis())) {
						 thezahlungFromDb.setZahlungBetrag(-lektion.getLektionPreis());
						 thezahlungFromDb.setZahlungDatum(lektion.getLektionDatum());	
						 zahlungRepository.save(thezahlungFromDb);		  
				  }

			}
			
			
			if (isInsert) {
		
				Date date = new Date();
				Zahlung thezahlung = new Zahlung();
				thezahlung.setZahlungDatum(date);
				thezahlung.setZahlungBetrag(-lektion.getLektionPreis());
				thezahlung.setZahlungKonto(" ");
				thezahlung.setZahlungSteuer(0);
				thezahlung.setZahlungRgnr(0);
				thezahlung.setZahlungKomm("Lektionsabrechnung");
				thezahlung.setZahlungAbrechnung(0);
				thezahlung.setCreatedAt(null);
				thezahlung.setUpdatedAt(null);
				thezahlung.setStudent(theStudentFromDb);
				thezahlung.setAgentur(theAgenturFromDb);
				thezahlung.setLektion(lektion);
				zahlungRepository.save(thezahlung);
				
			}
			
			thezalungsFromDb = zahlungRepository.findZahlungByStudentID(theStudentFromDb.getStudentIndex());
			if (thezalungsFromDb != null) {
				for (Zahlung var : thezalungsFromDb) {
					zahlungBetragSum = zahlungBetragSum + var.getZahlungBetrag();
				}
			}
			
			theStudentFromDb.setStudentKredit(zahlungBetragSum);
			

			if (theStudentFromDb.getStudentErsttermin() == null) {
				theStudentFromDb.setStudentErsttermin(lektion.getLektionDatum());
			}

			List<Lektion> allLektionsByStudentID = lektionRepository.findLektionByStudentID(theStudent.getStudentIndex());

			// Sorting Datum
			allLektionsByStudentID.sort(new Comparator<Lektion>() {
				@Override
				public int compare(Lektion lektionA, Lektion lektionB) {
					if (lektionA.getLektionDatum().getTime() < lektionB.getLektionDatum().getTime())
						return -1;
					else if (lektionA.getLektionDatum().getTime() == lektionB.getLektionDatum().getTime())
						return 0;
					else
						return 1;
				}
			});

			if (lektion.getLektionDatum().getTime() >= allLektionsByStudentID.get(allLektionsByStudentID.size() - 1)
					.getLektionDatum().getTime()) {
				theStudentFromDb.setStudentLetztermin(lektion.getLektionDatum());
			} else {
				theStudentFromDb.setStudentLetztermin(
						allLektionsByStudentID.get(allLektionsByStudentID.size() - 1).getLektionDatum());
			}
			
			studentRepository.save(theStudentFromDb);
		}

		

		return lektion;

	} catch (LektionNotFoundException e) {
		throw e;
	} catch (Exception e) {
		throw new LektionNotFoundException("Der Student ID: '"+
				lektion.getStudent().getStudentIndex() + "' oder Die Agentur ID: '"+
				lektion.getAgentur().getAgenturIndex() + "' is nicht vorhanden.");
	}

}
	
	public void deleteLektionById(long lektion_id) {
		
	List<Zahlung> thezalungsFromDb = null;
	double zahlungBetragSum = 0;
		
	Lektion theLektion = lektionRepository.findLektionByID(lektion_id);
		
		if(theLektion == null) {
			
			throw new LektionNotFoundException("Die Lektion ID: '"+ lektion_id + "'ist nicht vorhanden.");
		}	

	Student theStudent = theLektion.getStudent();
	theLektion.getZahlung().setZahlungBetrag(0);
	lektionRepository.save(theLektion);

	thezalungsFromDb = zahlungRepository.findZahlungByStudentID(theStudent.getStudentIndex());
	if (thezalungsFromDb != null) {
		for (Zahlung var : thezalungsFromDb) {
			zahlungBetragSum = zahlungBetragSum + var.getZahlungBetrag();
		}
	}
	
	theLektion.getStudent().setStudentKredit(zahlungBetragSum);

	theLektion.getZahlung().removeLektion();
	theLektion.getZahlung().removeStudent();
	theLektion.getZahlung().removeAgentur();
	zahlungRepository.delete(theLektion.getZahlung());
	lektionRepository.delete(theLektion);
}
	
	public List<Lektion> findLektionByStudentID(long student_id){
		
		return lektionRepository.findLektionByStudentID(student_id);
		
	}
	
}
