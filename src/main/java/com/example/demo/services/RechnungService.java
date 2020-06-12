package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Rechnung;
import com.example.demo.repositories.RechnungRepository;

@Service
public class RechnungService {
	
	@Autowired
	private RechnungRepository rechnungRepository;
	
	public Iterable<Rechnung> findAllRechnungs(){
		
		return rechnungRepository.findAll();		
		
	}
	
	/*public Rechnung findByRechnung_Index(int rechnung_index) {
		
		return rechnungRepository.findByRechnung_Index(rechnung_index);
	}*/
}
