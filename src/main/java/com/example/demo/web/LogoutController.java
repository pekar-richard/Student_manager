package com.example.demo.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin //Important to get acces from react app to server
public class LogoutController {
	
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

}
