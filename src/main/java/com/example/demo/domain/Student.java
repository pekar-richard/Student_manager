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
@Table(name="student")
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_index")
	private Long student_index;

	@Column(name="student_nachname")
	private String student_nachname;
	
	@Column(name="student_vorname")
	private String student_vorname;
	
	@Column(name="student_sortierung")
	private String student_sortierung;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="student_gebdat")
	private Date student_gebdat;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="student_ersttermin")
	private Date student_ersttermin;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="student_letztermin")
	private Date student_letztermin;
	
	@Column(name="student_preis45")
	private double student_preis45;
	
	@Column(name="student_preis60")
	private double student_preis60;
	
	@Column(name="student_preis90")
	private double student_preis90;
	
	@Column(name="student_preis120")
	private double student_preis120;
	
	@Column(name="student_abrechnung")
	private int student_abrechnung;
	
	@Column(name="student_kredit")
	private double student_kredit;
	
	@Column(name="student_agentur")
	private int student_agentur;
	
	@Column(name="student_aktiv")
	private int student_aktiv;
	
	@Column(name="student_quelle")
	private String student_quelle;
	
	@Column(name="student_komm")
	private String student_komm;
	
	public Student() {	
			
	}

	public Long getStudent_index() {
		return student_index;
	}

	public void setStudent_index(Long student_index) {
		this.student_index = student_index;
	}

	public String getStudent_nachname() {
		return student_nachname;
	}

	public void setStudent_nachname(String student_nachname) {
		this.student_nachname = student_nachname;
	}

	public String getStudent_vorname() {
		return student_vorname;
	}

	public void setStudent_vorname(String student_vorname) {
		this.student_vorname = student_vorname;
	}

	public String getStudent_sortierung() {
		return student_sortierung;
	}

	public void setStudent_sortierung(String student_sortierung) {
		this.student_sortierung = student_sortierung;
	}

	public Date getStudent_gebdat() {
		return student_gebdat;
	}

	public void setStudent_gebdat(Date student_gebdat) {
		this.student_gebdat = student_gebdat;
	}

	public Date getStudent_ersttermin() {
		return student_ersttermin;
	}

	public void setStudent_ersttermin(Date student_ersttermin) {
		this.student_ersttermin = student_ersttermin;
	}

	public Date getStudent_letztermin() {
		return student_letztermin;
	}

	public void setStudent_letztermin(Date student_letztermin) {
		this.student_letztermin = student_letztermin;
	}

	public double getStudent_preis45() {
		return student_preis45;
	}

	public void setStudent_preis45(double student_preis45) {
		this.student_preis45 = student_preis45;
	}

	public double getStudent_preis60() {
		return student_preis60;
	}

	public void setStudent_preis60(double student_preis60) {
		this.student_preis60 = student_preis60;
	}

	public double getStudent_preis90() {
		return student_preis90;
	}

	public void setStudent_preis90(double student_preis90) {
		this.student_preis90 = student_preis90;
	}

	public double getStudent_preis120() {
		return student_preis120;
	}

	public void setStudent_preis120(double student_preis120) {
		this.student_preis120 = student_preis120;
	}

	public int getStudent_abrechnung() {
		return student_abrechnung;
	}

	public void setStudent_abrechnung(int student_abrechnung) {
		this.student_abrechnung = student_abrechnung;
	}

	public double getStudent_kredit() {
		return student_kredit;
	}

	public void setStudent_kredit(double student_kredit) {
		this.student_kredit = student_kredit;
	}

	public int getStudent_agentur() {
		return student_agentur;
	}

	public void setStudent_agentur(int student_agentur) {
		this.student_agentur = student_agentur;
	}

	public int getStudent_aktiv() {
		return student_aktiv;
	}

	public void setStudent_aktiv(int student_aktiv) {
		this.student_aktiv = student_aktiv;
	}

	public String getStudent_quelle() {
		return student_quelle;
	}

	public void setStudent_quelle(String student_quelle) {
		this.student_quelle = student_quelle;
	}

	public String getStudent_komm() {
		return student_komm;
	}

	public void setStudent_komm(String student_komm) {
		this.student_komm = student_komm;
	}
	
	
}
