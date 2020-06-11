package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Lektion;
import com.example.demo.services.LektionService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin //Important to get acces from react app to server
public class LektionController {
	
	@Autowired
	private LektionService lektionService;
	
	@GetMapping("/allalektions")
	public Iterable<Lektion> getAllLektions(){return lektionService.findAllLektions();}

}
