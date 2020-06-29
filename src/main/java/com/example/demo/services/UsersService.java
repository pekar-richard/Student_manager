package com.example.demo.services;

import com.example.demo.domain.Users;

public interface UsersService {
	public Users findUserByName(String username);
}
