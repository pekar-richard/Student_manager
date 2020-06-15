package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Agentur;
import com.example.demo.domain.Lektion;
import com.example.demo.domain.Student;
import com.example.demo.exceptions.LektionNotFoundException;
import com.example.demo.repositories.AgenturRepository;
import com.example.demo.repositories.LektionRepository;
import com.example.demo.repositories.StudentRepository;

@Service
public class LektionService {
	
	@Autowired
	private LektionRepository lektionRepository;
	
	@Autowired
	private AgenturRepository agenturRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
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
				
				theStudent1.addLektion(lektion);
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
			
			return lektionRepository.save(lektion);
				
		}	
				
		}catch (LektionNotFoundException e){			
			throw e;
		}catch (Exception e){			
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
	

}
