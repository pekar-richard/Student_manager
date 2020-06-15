package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Agentur;
import com.example.demo.domain.Lektion;
import com.example.demo.domain.Rechnung;
import com.example.demo.domain.Student;
import com.example.demo.exceptions.AgenturNotFoundException;
import com.example.demo.repositories.AgenturRepository;
import com.example.demo.repositories.LektionRepository;
import com.example.demo.repositories.RechnungRepository;
import com.example.demo.repositories.StudentRepository;

@Service
public class AgenturService {
	
	@Autowired
	private AgenturRepository agenturRepository;
	
	public Iterable<Agentur> findAllAgenturs(){
		
		return agenturRepository.findAll();			
	}
	
	public Agentur findAgenturByID(long agentur_id) {
		
		Agentur theAgentur = agenturRepository.findAgenturByID(agentur_id);
				
			if(theAgentur == null) {	
				throw new AgenturNotFoundException("Die Agentur ID: '"+ agentur_id + "'is nicht vorhanden.");
			}
		
		return theAgentur;	
	}
	
	public Agentur saveOrUpdateAgentur(Agentur agentur) {
	
	try {	
		
		if(agentur.getAgentur_index() == null) {
			
			return agenturRepository.save(agentur);
			
		}else {
		
			Agentur theAgentur = findAgenturByID(agentur.getAgentur_index());
				if(theAgentur == null) {	
					throw new AgenturNotFoundException("Die Agentur ID: '"+ agentur.getAgentur_index() + "'is nicht vorhanden.");
				}
		
			return	agenturRepository.save(agentur);
			
		}	
			
	}catch (AgenturNotFoundException e){			
		throw e;
		}catch (Exception e){			
		throw new AgenturNotFoundException("Die Agentur ID: '"+ agentur.getAgentur_index() + "'is nicht vorhanden.");
	}

	}
	
	public void deleteAgenturById(long agentur_id) {
		
		
	Agentur theAgentur = agenturRepository.findAgenturByID(agentur_id);
	
	if(theAgentur == null) {
		
		throw new AgenturNotFoundException("Die Agentur ID: '"+ agentur_id + "'is nicht vorhanden.");
	}
		
	List<Student>  agenturStudents = theAgentur.getStudents();
	List<Lektion>  agenturLektions = theAgentur.getLektions();
	List<Rechnung> agenturRechnungs = theAgentur.getRechnungs();
	
	agenturStudents.forEach((n) ->  {n.removeAgentur();});
	agenturLektions.forEach((n) ->  {n.removeAgentur();});
	agenturRechnungs.forEach((n) -> {n.removeAgentur();});

	agenturRepository.delete(theAgentur);
	
	}
	
}
