package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
	
	@Override
	Iterable<Student> findAll();
	
}
