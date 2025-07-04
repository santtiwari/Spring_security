package com.becoder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	 protected void configure(HttpSecurity http) throws Exception {
	        http.csrf(customizer->customizer.disable());
	        http.authorizeRequests(request->request.anyRequest().authenticated());
            http.formLogin(Customizer.withDefaults());
            
            

    }
	

    
}
