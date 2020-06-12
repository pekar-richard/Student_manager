package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Lektion;
import com.example.demo.services.LektionService;
import com.example.demo.services.MapValidationErrorService;

@RestController
@RequestMapping("/api/lektion")
@CrossOrigin //Important to get acces from react app to server
public class LektionController {
	
	@Autowired
	private LektionService lektionService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@GetMapping("/allalektions")
	public Iterable<Lektion> getAllLektions(){return lektionService.findAllLektions();}

}
