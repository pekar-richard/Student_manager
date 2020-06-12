package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Agentur;
import com.example.demo.domain.Student;

@Repository
public interface AgenturRepository extends CrudRepository<Agentur,Long>{
	
	@Override
	Iterable<Agentur> findAll();
	
	@Query("from Agentur where agentur_index=:Agentur_index")  
	public Agentur findAgenturByID(@Param("Agentur_index") long u);

}
