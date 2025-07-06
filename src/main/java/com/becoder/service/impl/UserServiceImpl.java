package com.becoder.service.impl;

import com.becoder.dto.UserRequest;
import com.becoder.model.UserDtls;
import com.becoder.repository.userRepository;
import com.becoder.service.JwtService;
import com.becoder.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private userRepository userrepository;

    @Override
    public String login(UserRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            return jwtService.generateToken(request.getUsername());
        } catch (Exception ex) {
            throw new RuntimeException("Invalid username or password");
        }
    }

	@Override
	public List<UserDtls> getUserDtls() {
		
		return userrepository.findAll();
	}
    
}
