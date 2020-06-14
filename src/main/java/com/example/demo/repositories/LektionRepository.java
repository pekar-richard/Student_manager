package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Lektion;

@Repository
public interface LektionRepository extends CrudRepository<Lektion,Long>{
	
	@Override
	Iterable<Lektion> findAll();
	
	@Query("from Lektion where lektion_index=:Lektion_index")  
	public Lektion findLektionByID(@Param("Lektion_index") long u);
}
