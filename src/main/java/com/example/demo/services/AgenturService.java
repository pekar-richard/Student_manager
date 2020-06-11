package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Agentur;
import com.example.demo.repositories.AgenturRepository;

@Service
public class AgenturService {
	
	@Autowired
	private AgenturRepository agenturRepository;
	
	public Iterable<Agentur> findAllAgenturs(){
		
		return agenturRepository.findAll();		
		
	}
}
