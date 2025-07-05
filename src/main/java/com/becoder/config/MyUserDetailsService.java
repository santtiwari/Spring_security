package com.becoder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.becoder.model.UserDtls;
import com.becoder.repository.userRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private userRepository UserRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDtls user= UserRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new MyUserDetails(user);
	}

}
