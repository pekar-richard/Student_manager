package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Zahlung;

@Repository
public interface ZahlungRepository extends CrudRepository<Zahlung,Long>{
	
	@Override
	Iterable<Zahlung> findAll();
	
	@Query("from Zahlung where zahlung_index=:Zahlung_index")  
	public Zahlung findZahlungByID(@Param("Zahlung_index") long u);
}