package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Zahlung;
import com.example.demo.repositories.ZahlungRepository;

@Service
public class ZahlungService {
	
	@Autowired
	private ZahlungRepository zahlungRepository;
	
	public Iterable<Zahlung> findAllZahlungs(){
		
		return zahlungRepository.findAll();		
		
	}
	

}
