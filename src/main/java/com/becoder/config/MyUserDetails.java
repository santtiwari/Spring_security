package com.becoder.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.becoder.model.UserDtls;


public class MyUserDetails implements UserDetails {

	
	private UserDtls userDtls;
	
	
	/**
	 * @param userDtls the userDtls to set
	 */
	public void setUserDtls(UserDtls userDtls) {
		this.userDtls = userDtls;
	}

	public MyUserDetails() {
		super();
		
	}

	public MyUserDetails(UserDtls userDtls) {
		super();
		this.userDtls = userDtls;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		
		return userDtls.getPassword();
	}

	@Override
	public String getUsername() {
		
		return userDtls.getUsername();
	}
	public UserDtls getUserDtls() {
		return userDtls;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
