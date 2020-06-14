package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Rechnung;

@Repository
public interface RechnungRepository extends CrudRepository<Rechnung,Long> {
	
	@Override
	Iterable<Rechnung> findAll();
	
	@Query("from Rechnung where rechnung_index=:Rechnung_index")  
	public Rechnung findRechnungByID(@Param("Rechnung_index") long u);
}
