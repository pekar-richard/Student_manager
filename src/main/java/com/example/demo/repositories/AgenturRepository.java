package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Agentur;

@Repository
public interface AgenturRepository extends CrudRepository<Agentur,Long>{
	
	@Override
	Iterable<Agentur> findAll();

}
