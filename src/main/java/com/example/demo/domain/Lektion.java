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
@Table(name="lektion")
public class Lektion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lektion_index")
	private Long lektion_index;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="lektion_datum")
	private Date lektion_datum;
	
	@Column(name="lektion_student")
	private int lektion_student;
	
	@Column(name="lektion_min")
	private int lektion_min;
	
	@Column(name="lektion_preis")
	private double lektion_preis;
	
	@Column(name="lektion_art")
	private int lektion_art;
	
	@Column(name="lektion_status")
	private int lektion_status;

	@Column(name="lektion_agentur")
	private int lektion_agentur;
	
	@Column(name="lektion_abrechnung")
	private int lektion_abrechnung;
	
	@Column(name="lektion_rgnr")
	private int lektion_rgnr;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="lektion_bezahlt")
	private Date lektion_bezahlt;


}
