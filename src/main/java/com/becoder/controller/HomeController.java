package com.becoder.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.dto.UserRequest;
import com.becoder.model.UserDtls;
import com.becoder.service.UserService;

@RestController
public class HomeController {
	@Autowired
	private UserService userService;
	@GetMapping("/")
	public ResponseEntity<?> getDetails(HttpServletRequest request)
	{
		//String id = request.getSession().getId();
		return new ResponseEntity<>("Hello, welcome to Becoder Dashboard ",HttpStatus.OK);
	}
	@GetMapping("/user")
	public ResponseEntity<?> getUserDetails(HttpServletRequest request)
	{
		
		//String id = request.getSession().getId();
		return new ResponseEntity<>(userService.getUserDtls(),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest)
	{
		String token = userService.login(userRequest);
		if(token==null) {
			return new ResponseEntity<>("invalid credentials",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
	
}


