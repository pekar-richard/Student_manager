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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="created_at", updatable= false)
	private Date createdAt;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="updated_at")
	private Date updatedAt;
	
	//OneToOne with Zahlung
	@OneToOne(fetch = FetchType.LAZY,  mappedBy="lektion",  cascade= {CascadeType.PERSIST,
			 CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
	private Zahlung zahlung;
	
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
	
	public void removeZahlung() {	
		
		this.zahlung=null;
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

	public Zahlung getZahlung() {
		return zahlung;
	}

	public void setZahlung(Zahlung zahlung) {
		this.zahlung = zahlung;
	}

	@JsonProperty("studentIndex")
    public void setStudentById(Long studentIndex) {
        student = Student.fromId(studentIndex);
    }
	
	@JsonProperty("agenturIndex")
    public void setAgenturById(Long agenturIndex) {
        agentur = Agentur.fromId(agenturIndex);
    }
	
	public static Lektion fromId(Long lektion_index) {
		
		if (lektion_index == null) {
			return null;
		}
		Lektion lektion = new Lektion();
		lektion.lektionIndex = lektion_index;
	    return lektion;
	}
	
}
