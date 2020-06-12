package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Zahlung;
import com.example.demo.services.MapValidationErrorService;
import com.example.demo.services.ZahlungService;

@RestController
@RequestMapping("/api/zahlung")
@CrossOrigin //Important to get acces from react app to server
public class ZahlungController {
	
	@Autowired
	private ZahlungService zahlungService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@GetMapping("/allzahlungs")
	public Iterable<Zahlung> getAllZahlungs(){return zahlungService.findAllZahlungs();}

}
