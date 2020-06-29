package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Users;


public interface UsersRepository extends JpaRepository<Users, String>  {
	
	@Query("from Users where username=:Username")  
	public Users findUserByName(@Param("Username") String u);
}
