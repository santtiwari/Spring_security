package com.becoder.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/")
	public ResponseEntity<?> getDetails(HttpServletRequest request)
	{
		String id = request.getSession().getId();
		return new ResponseEntity<>("Hello, welcome to Becoder = "+id,HttpStatus.OK);
	}
	

}


