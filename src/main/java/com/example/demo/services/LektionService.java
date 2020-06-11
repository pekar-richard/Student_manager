package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Lektion;
import com.example.demo.repositories.LektionRepository;

@Service
public class LektionService {
	
	@Autowired
	private LektionRepository lektionRepository;
	
	public Iterable<Lektion> findAllLektions(){
		
		return lektionRepository.findAll();		
		
	}
}
