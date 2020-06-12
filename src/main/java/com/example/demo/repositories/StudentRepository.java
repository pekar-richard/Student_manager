package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
	
	@Override
	Iterable<Student> findAll();
	
	
	@Query("from Student where student_index=:Student_index")  
	public Student findStudentByID(@Param("Student_index") long u);
	
	//@Query("from Woerterbuch where username=:Username")  
	//List<Woerterbuch> findAllByName(@Param("Username") String u);
	
	//@Query("from Woerterbuch where status=:Status")  
	//List<Woerterbuch> findByStatus(@Param("Status")int s);
	
	//@Query("from Woerterbuch where username=:Username and wort_de LIKE CONCAT('%',:wort_de,'%')")
	//List<Woerterbuch> findByWort_DE(@Param("wort_de")String w, @Param("Username") String u);

	
	//@Query("from Woerterbuch where status=:status and username=:Username and wort_de LIKE CONCAT('%',:wort_de,'%')")
	//List<Woerterbuch> ByStatusAndWort_DE(@Param("status") int s, @Param("wort_de") String w, @Param("Username") String u);
	
}
