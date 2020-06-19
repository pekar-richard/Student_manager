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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Agentur;
import com.example.demo.services.AgenturService;
import com.example.demo.services.MapValidationErrorService;

@RestController
@RequestMapping("/api/agentur")
@CrossOrigin //Important to get acces from react app to server
public class AgenturController {
	
	@Autowired
	private AgenturService agenturService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@GetMapping("/allagenturs")
	public Iterable<Agentur> getAllAgenturs(){return agenturService.findAllAgenturs();}
	
	@GetMapping("/{agentur_id}")
	public ResponseEntity<?> findAgenturByID(@PathVariable long agentur_id){			

		Agentur theagentur= agenturService.findAgenturByID(agentur_id);
			
		return new ResponseEntity<Agentur>(theagentur, HttpStatus.OK);	
	}

	@PostMapping("/")
	public ResponseEntity<?> createNewAgentur(@Valid @RequestBody Agentur agentur, BindingResult result){	

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
		
		Agentur theagentur= agenturService.saveOrUpdateAgentur(agentur);
			
		return new ResponseEntity<Agentur>(theagentur, HttpStatus.CREATED);
	}
	
	@PutMapping("/{agentur_id}")
	public ResponseEntity<?> updateAgentur(@Valid @RequestBody Agentur agentur, BindingResult result, @PathVariable long agentur_id){	

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
		
		Agentur theagentur= agenturService.saveOrUpdateAgentur(agentur);
			
		return new ResponseEntity<Agentur>(theagentur, HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/{agentur_id}")	
	public ResponseEntity<?> deleteAgentur(@PathVariable long agentur_id ){
		
		agenturService.deleteAgenturById(agentur_id);
		
		return new ResponseEntity<String>("Agentur with ID:'" + agentur_id + "' was deleted", HttpStatus.OK);
		
	}
}
