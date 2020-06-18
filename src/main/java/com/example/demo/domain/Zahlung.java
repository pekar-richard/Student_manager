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
import javax.validation.constraints.NotNull;

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
	private Long zahlung_index;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="zahlung_datum")
	private Date zahlung_datum;
	
	@Column(name="zahlung_betrag")
	private double zahlung_betrag;
	
	@Column(name="zahlung_betragubrig")
	private double zahlung_betragubrig;
	
	@NotBlank(message="Bitte tragen Sie Zahlung_Konto ein.")
	@Column(name="zahlung_konto")
	private String zahlung_konto;
	
	@Column(name="zahlung_steuer")
	private int zahlung_steuer;
	
	@Column(name="zahlung_rgnr")
	private int zahlung_rgnr;
	
	@Column(name="zahlung_komm")
	private String zahlung_komm;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="created_at", updatable= false)
	private Date created_At;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="updated_at")
	private Date updated_At;
	
	//ManytoOne with Student
	@ManyToOne(fetch = FetchType.LAZY,cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="zahlung_student", nullable = false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "student_index")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("student_index")
	private Student student;
	
	public void removeStudent() {	
		
		this.student=null;
	}
	
	public Zahlung() {
		
	}
	
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();			
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();	
	}
	
	

	public double getZahlung_betragubrig() {
		return zahlung_betragubrig;
	}

	public void setZahlung_betragubrig(double zahlung_betragubrig) {
		this.zahlung_betragubrig = zahlung_betragubrig;
	}

	public Long getZahlung_index() {
		return zahlung_index;
	}

	public void setZahlung_index(Long zahlung_index) {
		this.zahlung_index = zahlung_index;
	}

	public Date getZahlung_datum() {
		return zahlung_datum;
	}

	public void setZahlung_datum(Date zahlung_datum) {
		this.zahlung_datum = zahlung_datum;
	}

	public double getZahlung_betrag() {
		return zahlung_betrag;
	}

	public void setZahlung_betrag(double zahlung_betrag) {
		this.zahlung_betrag = zahlung_betrag;
	}

	public String getZahlung_konto() {
		return zahlung_konto;
	}

	public void setZahlung_konto(String zahlung_konto) {
		this.zahlung_konto = zahlung_konto;
	}

	public int getZahlung_steuer() {
		return zahlung_steuer;
	}

	public void setZahlung_steuer(int zahlung_steuer) {
		this.zahlung_steuer = zahlung_steuer;
	}

	public int getZahlung_rgnr() {
		return zahlung_rgnr;
	}

	public void setZahlung_rgnr(int zahlung_rgnr) {
		this.zahlung_rgnr = zahlung_rgnr;
	}

	public String getZahlung_komm() {
		return zahlung_komm;
	}

	public void setZahlung_komm(String zahlung_komm) {
		this.zahlung_komm = zahlung_komm;
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}	
		
	public static Zahlung fromId(Long zahlung_index) {
		
		if (zahlung_index == null) {
			return null;
		}
		Zahlung zahlung = new Zahlung();
		zahlung.zahlung_index = zahlung_index;
	    return zahlung;
	}
	
	@JsonProperty("student_index")
    public void setStudentById(Long student_index) {
        student = Student.fromId(student_index);
    }
}
