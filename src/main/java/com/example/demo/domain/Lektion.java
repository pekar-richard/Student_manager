package com.example.demo.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="lektion")
public class Lektion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lektion_index")
	private Long lektion_index;
	
	@NotBlank(message="Bitte wählen Sie Lektion_Datum.")
	@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
	@Column(name="lektion_datum")
	private Date lektion_datum;
	
	@NotBlank(message="Bitte wählen Sie Lektion_Min.")
	@Column(name="lektion_min")
	private int lektion_min;
	
	@NotBlank(message="Bitte tragen Sie Lektion_Preis ein.")
	@Column(name="lektion_preis")
	private double lektion_preis;
	
	@Column(name="lektion_art")
	private int lektion_art;
	
	@Column(name="lektion_status")
	private int lektion_status;
	
	@Column(name="lektion_abrechnung")
	private int lektion_abrechnung;
	
	@Column(name="lektion_rgnr")
	private int lektion_rgnr;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="lektion_bezahlt")
	private Date lektion_bezahlt;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="created_at", updatable= false)
	private Date created_At;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="updated_at")
	private Date updated_At;
	
	//ManytoOne with Agentur
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="lektion_agentur", nullable = true)
	@JsonIgnore
	private Agentur agentur;
	
	//ManytoOne with Student
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="lektion_student", nullable = true)
	@JsonIgnore
	private Student student;
	
	public Lektion() {
		
	}
	
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();			
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();	
	}

	public Long getLektion_index() {
		return lektion_index;
	}

	public void setLektion_index(Long lektion_index) {
		this.lektion_index = lektion_index;
	}

	public Date getLektion_datum() {
		return lektion_datum;
	}

	public void setLektion_datum(Date lektion_datum) {
		this.lektion_datum = lektion_datum;
	}

	public int getLektion_min() {
		return lektion_min;
	}

	public void setLektion_min(int lektion_min) {
		this.lektion_min = lektion_min;
	}

	public double getLektion_preis() {
		return lektion_preis;
	}

	public void setLektion_preis(double lektion_preis) {
		this.lektion_preis = lektion_preis;
	}

	public int getLektion_art() {
		return lektion_art;
	}

	public void setLektion_art(int lektion_art) {
		this.lektion_art = lektion_art;
	}

	public int getLektion_status() {
		return lektion_status;
	}

	public void setLektion_status(int lektion_status) {
		this.lektion_status = lektion_status;
	}

	public int getLektion_abrechnung() {
		return lektion_abrechnung;
	}

	public void setLektion_abrechnung(int lektion_abrechnung) {
		this.lektion_abrechnung = lektion_abrechnung;
	}

	public int getLektion_rgnr() {
		return lektion_rgnr;
	}

	public void setLektion_rgnr(int lektion_rgnr) {
		this.lektion_rgnr = lektion_rgnr;
	}

	public Date getLektion_bezahlt() {
		return lektion_bezahlt;
	}

	public void setLektion_bezahlt(Date lektion_bezahlt) {
		this.lektion_bezahlt = lektion_bezahlt;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public Date getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}

	public Agentur getAgentur() {
		return agentur;
	}

	public void setAgentur(Agentur agentur) {
		this.agentur = agentur;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
