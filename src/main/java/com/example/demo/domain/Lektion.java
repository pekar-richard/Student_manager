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
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="lektion")
public class Lektion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lektion_index")
	private Long lektionIndex;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="lektion_datum")
	private Date lektionDatum;
	
	@Column(name="lektion_min")
	private int lektionMin;
	
	@Digits(integer=3, fraction=2)
	@Column(name="lektion_preis")
	private double lektionPreis;
	
	@Column(name="lektion_art")
	private int lektionArt;
	
	@Column(name="lektion_status")
	private int lektionStatus;
	
	@Column(name="lektion_abrechnung")
	private int lektionAbrechnung;
	
	@Digits(integer=8,fraction=0)
	@Column(name="lektion_rgnr")
	private int lektionRgnr;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="lektion_bezahlt")
	private Date lektionBezahlt;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="created_at", updatable= false)
	private Date createdAt;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="updated_at")
	private Date updatedAt;
	
	//ManytoOne with Agentur
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="lektion_agentur", nullable = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "agenturIndex")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("agenturIndex")
	private Agentur agentur;
	
	//ManytoOne with Student
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="lektion_student", nullable = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "studentIndex")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("studentIndex")
	private Student student;
	
	public Lektion() {
		
	}
	
	public void removeAgentur() {	
		
		this.agentur=null;
	}
	
	public void removeStudent() {	
		
		this.student=null;
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();			
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();	
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
	
	public static Lektion fromId(Long lektion_index) {
		
		if (lektion_index == null) {
			return null;
		}
		Lektion lektion = new Lektion();
		lektion.lektionIndex = lektion_index;
	    return lektion;
	}
	
	
	public Long getLektionIndex() {
		return lektionIndex;
	}

	public void setLektionIndex(Long lektionIndex) {
		this.lektionIndex = lektionIndex;
	}

	public Date getLektionDatum() {
		return lektionDatum;
	}

	public void setLektionDatum(Date lektionDatum) {
		this.lektionDatum = lektionDatum;
	}

	public int getLektionMin() {
		return lektionMin;
	}

	public void setLektionMin(int lektionMin) {
		this.lektionMin = lektionMin;
	}

	public double getLektionPreis() {
		return lektionPreis;
	}

	public void setLektionPreis(double lektionPreis) {
		this.lektionPreis = lektionPreis;
	}

	public int getLektionArt() {
		return lektionArt;
	}

	public void setLektionArt(int lektionArt) {
		this.lektionArt = lektionArt;
	}

	public int getLektionStatus() {
		return lektionStatus;
	}

	public void setLektionStatus(int lektionStatus) {
		this.lektionStatus = lektionStatus;
	}

	public int getLektionAbrechnung() {
		return lektionAbrechnung;
	}

	public void setLektionAbrechnung(int lektionAbrechnung) {
		this.lektionAbrechnung = lektionAbrechnung;
	}

	public int getLektionRgnr() {
		return lektionRgnr;
	}

	public void setLektionRgnr(int lektionRgnr) {
		this.lektionRgnr = lektionRgnr;
	}

	public Date getLektionBezahlt() {
		return lektionBezahlt;
	}

	public void setLektionBezahlt(Date lektionBezahlt) {
		this.lektionBezahlt = lektionBezahlt;
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

	@JsonProperty("studentIndex")
    public void setStudentById(Long studentIndex) {
        student = Student.fromId(studentIndex);
    }
	
	@JsonProperty("agenturIndex")
    public void setAgenturById(Long agenturIndex) {
        agentur = Agentur.fromId(agenturIndex);
    }
}
