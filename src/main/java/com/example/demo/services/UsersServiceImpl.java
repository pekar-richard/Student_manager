package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Users;
import com.example.demo.repositories.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
	private UsersRepository usersRepository;
	
	@Autowired
	public UsersServiceImpl(UsersRepository theusersRepository) {	
		usersRepository=theusersRepository;		
	}

	@Override
	public Users findUserByName(String username) {
		
		return usersRepository.findUserByName(username);
	}

}
