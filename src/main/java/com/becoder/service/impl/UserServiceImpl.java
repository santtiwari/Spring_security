package com.becoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.becoder.dto.UserRequest;
import com.becoder.service.JwtService;
import com.becoder.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationmaneger;
	@Override
	public String login(UserRequest request) {
		Authentication authenticate = authenticationmaneger.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		if(authenticate.isAuthenticated()) {
			//return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
			return jwtService.genrateToken(request.getUsername());
		}
		return null;
	}

}
