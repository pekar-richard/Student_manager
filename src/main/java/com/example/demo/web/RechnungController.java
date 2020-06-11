package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Rechnung;
import com.example.demo.services.RechnungService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin //Important to get acces from react app to server
public class RechnungController {
	
	@Autowired
	private RechnungService rechnungService;
	
	@GetMapping("/allarechnungs")
	public Iterable<Rechnung> getAllRechnungs(){return rechnungService.findAllRechnungs();}

}
