package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Agentur;
import com.example.demo.services.AgenturService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin //Important to get acces from react app to server
public class AgenturController {
	
	@Autowired
	private AgenturService agenturService;
	
	@GetMapping("/allagenturs")
	public Iterable<Agentur> getAllAgenturs(){return agenturService.findAllAgenturs();}


}
