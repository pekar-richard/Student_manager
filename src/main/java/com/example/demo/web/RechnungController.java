package com.example.demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Rechnung;
import com.example.demo.services.MapValidationErrorService;
import com.example.demo.services.RechnungService;

@RestController
@RequestMapping("/api/rechnung")
@CrossOrigin //Important to get acces from react app to server
public class RechnungController {
	
	@Autowired
	private RechnungService rechnungService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@GetMapping("/allarechnungs")
	public Iterable<Rechnung> getAllRechnungs(){return rechnungService.findAllRechnungs();}

	@GetMapping("/{rechnung_id}")
	public ResponseEntity<?> findRechnungByID(@PathVariable long rechnung_id){			

	Rechnung theRechnung = rechnungService.findRechnungByID(rechnung_id);
		
	return new ResponseEntity<Rechnung>(theRechnung, HttpStatus.OK);
	}
	
	@PutMapping("/{student_id}/{agentur_id}")
	public ResponseEntity<?> createNewRechnung(@Valid @RequestBody Rechnung rechnung, BindingResult result, @PathVariable long agentur_id, @PathVariable long student_id ){			

	ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
	if(errorMap!=null) return errorMap;
	
	Rechnung theRechnung= rechnungService.saveOrUpdateRechnung(rechnung, student_id, agentur_id);
		
	return new ResponseEntity<Rechnung>(theRechnung, HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/{rechnung_id}")	
	public ResponseEntity<?> deleteRechnung(@PathVariable long rechnung_id){
		
	rechnungService.deleteRechnungById(rechnung_id);
		
	return new ResponseEntity<String>("Die Rechnung mit ID:'" + rechnung_id + "' war geloescht", HttpStatus.OK);
	}
}
