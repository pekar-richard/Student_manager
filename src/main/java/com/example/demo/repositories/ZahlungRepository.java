package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Zahlung;

@Repository
public interface ZahlungRepository extends CrudRepository<Zahlung,Long>{
	
	@Override
	Iterable<Zahlung> findAll();
}
