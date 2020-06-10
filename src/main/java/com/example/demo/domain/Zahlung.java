package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="zahlung")
public class Zahlung {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="zahlung_index")
	private Long zahlung_index;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="zahlung_datum")
	private Date zahlung_datum;
	
	@Column(name="zahlung_student")
	private int zahlung_student;
	
	@Column(name="zahlung_betrag")
	private double zahlung_betrag;
	
	@Column(name="zahlung_steuer")
	private int zahlung_steuer;
	
	@Column(name="zahlung_rgnr")
	private int zahlung_rgnr;
	
	@Column(name="zahlung_komm", unique = true)
	private String zahlung_komm;
	
	

}
