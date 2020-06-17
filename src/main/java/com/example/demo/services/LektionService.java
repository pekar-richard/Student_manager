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
	
	Date lastDate;
	private double theStudentPreis = 0;
	
	
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
	
	public Lektion saveOrUpdateLektion(Lektion lektion, long student_id, long agentur_id) {
	try {	
		
		Agentur theAgentur = agenturRepository.findAgenturByID(agentur_id);
		Student theStudent1 = studentRepository.findStudentByID(student_id);
		
		if(theAgentur == null && theStudent1 == null) {
			
			throw new LektionNotFoundException("Der Student ID: '"+ student_id + "' oder Die Agentur ID: '" + agentur_id + "' is nicht vorhanden.");
		}
		
		if(lektion.getLektion_index() == null) {
		 
			
			if( theAgentur!= null) {
				
				theAgentur.addLektion(lektion);
			}
	
			
			if( theStudent1!= null) {
				
				if(theStudent1.getStudent_ersttermin()== null) {
					theStudent1.setStudent_ersttermin(lektion.getLektion_datum());			
				}
				theStudent1.setStudent_letztermin(lektion.getLektion_datum());
				
				theStudent1.addLektion(lektion);	
				
				
				lektion.setLektion_abrechnung(lektion.getLektion_abrechnung());
				
				switch(lektion.getLektion_min()) {
				  case 45:
					  lektion.setLektion_preis(theStudent1.getStudent_preis45());
				    break;
				  case 60:
					  lektion.setLektion_preis(theStudent1.getStudent_preis60());
				    break;
				  case 90:
					  lektion.setLektion_preis(theStudent1.getStudent_preis90());
					    break;
				  case 120:
					  lektion.setLektion_preis(theStudent1.getStudent_preis120());
					    break;
				  default:
					  throw new LektionNotFoundException("Die Lektion_min ist nicht vorhanden.");
				}
				
			
				double theStudentKredit = lektion.getStudent().getStudent_kredit();
				double theLektionPreis = lektion.getLektion_preis();			
				
				switch(lektion.getLektion_abrechnung()) {
				  case 1:
				    break;
				  case 2:
					  theStudent1.setStudent_kredit(theStudentKredit-theLektionPreis);
					  List<Zahlung> theStudentZahlungen= theStudent1.getZahlungs();
					  
					  for (int i = 0; i < theStudentZahlungen.size(); i++) {
						  if(theStudentZahlungen.get(i).getZahlung_betragubrig()>0 && theStudentZahlungen.get(i).getZahlung_betragubrig()>=lektion.getLektion_preis())
						  {theStudentZahlungen.get(i).setZahlung_betragubrig(theStudentZahlungen.get(i).getZahlung_betragubrig()-lektion.getLektion_preis());break;}
						}
					 
				    break;
				  case 3:  
					    break;
				  default:
					  throw new LektionNotFoundException("Die Lektion_abrechnung ist nicht vorhanden.");
				}

			}
			
	
			return lektionRepository.save(lektion);
			
		}else {
				 Lektion theLektion = lektionRepository.findLektionByID(lektion.getLektion_index());
				if(theLektion == null  ) {	
					throw new LektionNotFoundException("Die Lektion ID: '"+ lektion.getLektion_index() + "'ist nicht vorhanden.");
			}
			
				Agentur theagentur = agenturRepository.findAgenturByID(agentur_id);
				theagentur.addLektion(lektion);
				Student theStudent = studentRepository.findStudentByID(student_id);
				theStudent.addLektion(lektion);	
							
			lektion.setLektion_abrechnung(lektion.getLektion_abrechnung());
				
				switch(lektion.getLektion_min()) {
				  case 45:
					  lektion.setLektion_preis(theStudent.getStudent_preis45());
					theStudentPreis = theStudent.getStudent_preis45();
				    break;
				  case 60:
					  lektion.setLektion_preis(theStudent.getStudent_preis60());
					  theStudentPreis = theStudent.getStudent_preis60();
				    break;
				  case 90:
					  lektion.setLektion_preis(theStudent.getStudent_preis90());
					  theStudentPreis = theStudent.getStudent_preis90();
					    break;
				  case 120:
					  lektion.setLektion_preis(theStudent.getStudent_preis120());
					  theStudentPreis = theStudent.getStudent_preis120();
					    break;
				  default:
					  throw new LektionNotFoundException("Die Lektion_min ist nicht vorhanden.");
				}
				
				double theStudentKredit = lektion.getStudent().getStudent_kredit();
				double theLektionPreis = lektion.getLektion_preis();			
				
				switch(lektion.getLektion_abrechnung()) {
				  case 1:
				    break;
				  case 2:
					  if(theStudentPreis != theLektionPreis) {
						  theStudent.setStudent_kredit(theStudentKredit-theLektionPreis);
					  }					 
				    break;
				  case 3:  
					    break;
				  default:
					  throw new LektionNotFoundException("Die Lektion_abrechnung ist nicht vorhanden.");
				}
				
				
				Lektion theLektionSave = lektionRepository.save(lektion);
				
				List<Lektion> theLektionStudents = lektionRepository.findLektionByStudentID(student_id);
				lastDate = theLektionStudents.get(0).getLektion_datum();
				
				if(lastDate!=null) {
					
					theLektionStudents.forEach((n) ->  {	
						
						Date date1 = n.getLektion_datum();
						if(!date1.before(lastDate))
						
						{lastDate=date1;}
				
		                });
					
					theStudent.setStudent_letztermin(lastDate);
					studentRepository.save(theStudent);
	
				}
		
			return theLektionSave;
				
		}	
				
		}catch (LektionNotFoundException e){			
			throw e;
		}catch (Exception err){	
		
			throw new LektionNotFoundException("Der Student ID: '"+ student_id + "' oder Die Agentur ID: '" + agentur_id + "' is nicht vorhanden.");
		}
	}
	
	public void deleteLektionById(long lektion_id) {
	
	
	Lektion theLektion = lektionRepository.findLektionByID(lektion_id);
	
	if(theLektion == null) {
		
		throw new LektionNotFoundException("Die Lektion ID: '"+ lektion_id + "'ist nicht vorhanden.");
	}

	theLektion.removeAgentur();
	theLektion.removeStudent(); 
	lektionRepository.delete(theLektion);
}
	
	public Iterable<Lektion> findLektionByStudentID(long student_id){
		
		return lektionRepository.findLektionByStudentID(student_id);
		
	}
	

}
