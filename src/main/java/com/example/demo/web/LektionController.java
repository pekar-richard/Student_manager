package com.example.demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/{lektion_id}")
	public ResponseEntity<?> findLektionByID(@PathVariable long lektion_id){			

	Lektion theLektion = lektionService.findLektionByID(lektion_id);
		
	return new ResponseEntity<Lektion>(theLektion, HttpStatus.OK);
	}
	
	@PutMapping("/{student_id}/{agentur_id}")
	public ResponseEntity<?> createNewLektion(@Valid @RequestBody Lektion lektion, BindingResult result, @PathVariable long agentur_id, @PathVariable long student_id ){			

	ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
	if(errorMap!=null) return errorMap;
	
	Lektion theLektion= lektionService.saveOrUpdateLektion(lektion, student_id, agentur_id);
		
	return new ResponseEntity<Lektion>(theLektion, HttpStatus.CREATED);	
	}
	
}
