package com.becoder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	 protected void configure(HttpSecurity http) throws Exception {
	        http.csrf(customizer->customizer.disable());
	        http.authorizeRequests(request->request.anyRequest().authenticated());
            //http.formLogin(Customizer.withDefaults());
	        http.httpBasic(Customizer.withDefaults());
            
            }
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider()
		{
		 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		 daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		 return daoAuthenticationProvider;
		}
	

    
}
