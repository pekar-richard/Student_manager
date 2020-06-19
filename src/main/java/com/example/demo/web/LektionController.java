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
	
	@GetMapping("/alllektions")
	public Iterable<Lektion> getAllLektions(){return lektionService.findAllLektions();}

	@GetMapping("/{lektion_id}")
	public ResponseEntity<?> findLektionByID(@PathVariable long lektion_id){			

	Lektion theLektion = lektionService.findLektionByID(lektion_id);
		
	return new ResponseEntity<Lektion>(theLektion, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createNewLektion(@Valid @RequestBody Lektion lektion, BindingResult result){			

	ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
	if(errorMap!=null) return errorMap;
	
	Lektion theLektion= lektionService.saveOrUpdateLektion(lektion);
		
	return new ResponseEntity<Lektion>(theLektion, HttpStatus.CREATED);	
	}
	
	@PutMapping("/{lektion_id}")
	public ResponseEntity<?> updateLektion(@Valid @RequestBody Lektion lektion, BindingResult result, @PathVariable long lektion_id){			

	ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
	if(errorMap!=null) return errorMap;
	
	Lektion theLektion= lektionService.saveOrUpdateLektion(lektion);
		
	return new ResponseEntity<Lektion>(theLektion, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{lektion_id}")	
	public ResponseEntity<?> deleteLektion(@PathVariable long lektion_id ){
		
	lektionService.deleteLektionById(lektion_id);
		
	return new ResponseEntity<String>("Die Lektion mit ID:'" + lektion_id + "' war geloescht", HttpStatus.OK);
	}
	
}
