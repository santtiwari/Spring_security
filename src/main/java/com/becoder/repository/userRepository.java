package com.becoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.model.UserDtls;

public interface userRepository extends JpaRepository<UserDtls, Integer>{

	UserDtls findByUsername(String username);

}
