package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Rechnung;

@Repository
public interface RechnungRepository extends CrudRepository<Rechnung,Long> {
	
	@Override
	Iterable<Rechnung> findAll();
	
	//Rechnung findByRechnung_Index(int rechnung_index);
}
