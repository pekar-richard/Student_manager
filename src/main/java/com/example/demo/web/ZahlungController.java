package com.example.demo.web;

import java.util.List;

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
	
	@GetMapping("/{student_id}/{agentur_id}")
	public ResponseEntity<?> findZahlungByStudentAndAgentur(@PathVariable long student_id, @PathVariable long agentur_id)
	{
		
		List<Zahlung> theZahlung = zahlungService.findZahlungByStudentAndAgentur(student_id,agentur_id);
		
		return new ResponseEntity<List<Zahlung>>(theZahlung, HttpStatus.OK);
	}

	@GetMapping("/{zahlung_id}")
	public ResponseEntity<?> findZahlungByID(@PathVariable long zahlung_id){			

		Zahlung theZahlung = zahlungService.findZahlungByID(zahlung_id);
			
		return new ResponseEntity<Zahlung>(theZahlung, HttpStatus.OK);
	}
	
	@GetMapping("/student/{student_id}")
	public ResponseEntity<?> findZahlungByStudentID(@PathVariable long student_id){			

	List<Zahlung> theZahlung = zahlungService.findZahlungByStudentID(student_id);
		
	return new ResponseEntity<List<Zahlung>>(theZahlung, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createNewZahlung(@Valid @RequestBody Zahlung zahlung, BindingResult result){			

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
		
		Zahlung theZahlung= zahlungService.saveOrUpdateZahlung(zahlung);
			
		return new ResponseEntity<Zahlung>(theZahlung, HttpStatus.CREATED);	
	}
	
	@PutMapping("/{zahlung_id}")
	public ResponseEntity<?> updateZahlung(@Valid @RequestBody Zahlung zahlung, BindingResult result, @PathVariable long zahlung_id ){			

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
		
		Zahlung theZahlung= zahlungService.saveOrUpdateZahlung(zahlung);
			
		return new ResponseEntity<Zahlung>(theZahlung, HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/{zahlung_id}")	
	public ResponseEntity<?> deleteZahlung(@PathVariable long zahlung_id ){
		
		zahlungService.deleteZahlungById(zahlung_id);
		
		return new ResponseEntity<String>("Die Zahlung mit ID:'" + zahlung_id + "' war geloescht", HttpStatus.OK);
		
	}
}
