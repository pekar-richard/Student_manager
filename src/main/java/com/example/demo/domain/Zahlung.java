package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="zahlung")
public class Zahlung {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="zahlung_index")
	private Long zahlungIndex;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="zahlung_datum")
	private Date zahlungDatum;
	
	@Column(name="zahlung_betrag")
	private double zahlungBetrag;
	
	@Column(name="zahlung_konto")
	private String zahlungKonto;
	
	@Column(name="zahlung_steuer")
	private int zahlungSteuer;
	
	@Column(name="zahlung_rgnr")
	private int zahlungRgnr;
	
	@Column(name="zahlung_komm")
	private String zahlungKomm;
	
	@Column(name="zahlung_abrechnung")
	private int lektionAbrechnung;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="created_at", updatable= false)
	private Date createdAt;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="updated_at")
	private Date updatedAt;
	
	//ManytoOne with Student
	@ManyToOne(fetch = FetchType.LAZY,cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="zahlung_student", nullable = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "studentIndex")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("studentIndex")
	private Student student;
	
	//@OneToOne with Lektion
	@OneToOne(fetch = FetchType.LAZY,cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="zahlung_lektion", nullable = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "lektionIndex")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("lektionIndex")
	private Lektion lektion;

	public void removeStudent() {	
		
		this.student=null;
	}
	
	
	public void removeLektion() {	
		
		this.lektion=null;
	}
	
	public int getLektionAbrechnung() {
		return lektionAbrechnung;
	}

	public void setLektionAbrechnung(int lektionAbrechnung) {
		this.lektionAbrechnung = lektionAbrechnung;
	}

	public Lektion getLektion() {
		return lektion;
	}

	public void setLektion(Lektion lektion) {
		this.lektion = lektion;
	}
	
	public Zahlung() {
		
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();			
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();	
	}
	
	public void setStudent(Student student) {	
		
		this.student = student;
	}
	
	public Student getStudent() {
		return student;
	}	
		
	public Long getZahlungIndex() {
		return zahlungIndex;
	}

	public void setZahlungIndex(Long zahlungIndex) {
		this.zahlungIndex = zahlungIndex;
	}

	public Date getZahlungDatum() {
		return zahlungDatum;
	}

	public void setZahlungDatum(Date zahlungDatum) {
		this.zahlungDatum = zahlungDatum;
	}

	public double getZahlungBetrag() {
		return zahlungBetrag;
	}

	public void setZahlungBetrag(double zahlungBetrag) {
		this.zahlungBetrag = zahlungBetrag;
	}

	public String getZahlungKonto() {
		return zahlungKonto;
	}

	public void setZahlungKonto(String zahlungKonto) {
		this.zahlungKonto = zahlungKonto;
	}

	public int getZahlungSteuer() {
		return zahlungSteuer;
	}

	public void setZahlungSteuer(int zahlungSteuer) {
		this.zahlungSteuer = zahlungSteuer;
	}

	public int getZahlungRgnr() {
		return zahlungRgnr;
	}

	public void setZahlungRgnr(int zahlungRgnr) {
		this.zahlungRgnr = zahlungRgnr;
	}

	public String getZahlungKomm() {
		return zahlungKomm;
	}

	public void setZahlungKomm(String zahlungKomm) {
		this.zahlungKomm = zahlungKomm;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public static Zahlung fromId(Long zahlungIndex) {
		
		if (zahlungIndex == null) {
			return null;
		}
		Zahlung zahlung = new Zahlung();
		zahlung.zahlungIndex = zahlungIndex;
	    return zahlung;
	}
	
	
	@JsonProperty("studentIndex")
    public void setStudentById(Long studentIndex) {
        student = Student.fromId(studentIndex);
    }
	
	@JsonProperty("lektionIndex")
    public void setLektionById(Long lektionIndex) {
        lektion = Lektion.fromId(lektionIndex);
    }
}
