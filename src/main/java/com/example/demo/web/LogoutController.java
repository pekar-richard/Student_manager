package com.example.demo.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.AgenturNotFoundException;
import com.example.demo.forms.LoginForm;


@RestController
@RequestMapping("/api")
public class LogoutController {

	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@GetMapping("/logout")
    public ResponseEntity<?> fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
          
        return new ResponseEntity<Authentication>(auth, HttpStatus.OK);
    }
	
	@GetMapping("/getuser")
    public  ResponseEntity<?> getUser(HttpServletRequest request, HttpServletResponse response) {           
		
		  Principal user = request.getUserPrincipal();
		  System.out.println(user);
          if(user!=null) {
        	  
        	  String username = request.getUserPrincipal().getName();
    		
        	  return new ResponseEntity<String>(username, HttpStatus.OK);	
          }else {
        	  return new ResponseEntity<String>("null", HttpStatus.OK);	
        	  
          }
               
    }
	
	@PostMapping("/login")
	public ResponseEntity<?> loginStudent(HttpServletRequest request, @RequestBody LoginForm loginForm){
	
	try {
			
		UsernamePasswordAuthenticationToken authReq
	      = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
	    Authentication auth = authenticationManager.authenticate(authReq);
	   
	    
	    SecurityContext sc = SecurityContextHolder.getContext();
	    sc.setAuthentication(auth);
	    HttpSession session = request.getSession(true);
	    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

		 Principal user = request.getUserPrincipal();
	    if(user!=null ) {
	    	
	  	  String username = request.getUserPrincipal().getName();
	  	
	  	  return new ResponseEntity<String>(username, HttpStatus.OK);	
	    }else {
	  	  return new ResponseEntity<String>("null", HttpStatus.OK);	
	  	  
	    }	
	      
	}catch (Exception e){			
		return new ResponseEntity<String>("true", HttpStatus.OK);
	}
	
    	
}


}
