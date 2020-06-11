package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Lektion;

@Repository
public interface LektionRepository extends CrudRepository<Lektion,Long>{
	
	@Override
	Iterable<Lektion> findAll();
}
