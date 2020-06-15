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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="rechnung")
public class Rechnung {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rechn_index")
	private Long rechn_index;
	
	@Column(name="rechn_typ")
	private int rechn_typ;

	@NotBlank(message="Bitte tragen Sie Rechn_Name ein.")
	@Column(name="rechn_name")
	private String rechn_name;
	
	@NotBlank(message="Bitte tragen Sie Rechn_Zusatz. ein.")
	@Column(name="rechn_zusatz")
	private String rechn_zusatz;
	
	@NotBlank(message="Bitte tragen Sie Rechn_Str. ein.")
	@Column(name="rechn_str")
	private String rechn_str;
	
	@NotBlank(message="Bitte tragen Sie Rechn_PLZ ein.")
	@Column(name="rechn_plz")
	private String rechn_plz;
	
	@NotBlank(message="Bitte tragen Sie Rechn_Ort ein.")
	@Column(name="rechn_ort")
	private String rechn_ort;
	
	@NotBlank(message="Bitte tragen Sie Rechn_Land ein.")
	@Column(name="rechn_land")
	private String rechn_land;
	
	@NotNull(message="Bitte tragen Sie Rechn_ICO ein.")
	@Column(name="rechn_ico")
	private int rechn_ico;
	
	@NotNull(message="Bitte tragen Sie Rechn_DIC ein.")
	@Column(name="rechn_dic")
	private int rechn_dic;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="created_at", updatable= false)
	private Date created_At;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="updated_at")
	private Date updated_At;
	
	//ManytoOne with Agentur
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="rechn_agentur", nullable = true)
	@JsonIgnore
	private Agentur agentur;
	
	//ManytoOne with Student
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="rechn_student", nullable = true)
	@JsonIgnore
	private Student student;
	
	public Rechnung() {
		
	}
	
	public void removeAgentur() {	
		
		this.agentur=null;
	}
	
	public void removeStudent() {	
		
		this.student=null;
	}
	
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();			
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();	
	}

	public Long getRechn_index() {
		return rechn_index;
	}

	public void setRechn_index(Long rechn_index) {
		this.rechn_index = rechn_index;
	}

	public int getRechn_typ() {
		return rechn_typ;
	}

	public void setRechn_typ(int rechn_typ) {
		this.rechn_typ = rechn_typ;
	}

	public String getRechn_name() {
		return rechn_name;
	}

	public void setRechn_name(String rechn_name) {
		this.rechn_name = rechn_name;
	}

	public String getRechn_zusatz() {
		return rechn_zusatz;
	}

	public void setRechn_zusatz(String rechn_zusatz) {
		this.rechn_zusatz = rechn_zusatz;
	}

	public String getRechn_str() {
		return rechn_str;
	}

	public void setRechn_str(String rechn_str) {
		this.rechn_str = rechn_str;
	}

	public String getRechn_plz() {
		return rechn_plz;
	}

	public void setRechn_plz(String rechn_plz) {
		this.rechn_plz = rechn_plz;
	}

	public String getRechn_ort() {
		return rechn_ort;
	}

	public void setRechn_ort(String rechn_ort) {
		this.rechn_ort = rechn_ort;
	}

	public String getRechn_land() {
		return rechn_land;
	}

	public void setRechn_land(String rechn_land) {
		this.rechn_land = rechn_land;
	}

	public int getRechn_ico() {
		return rechn_ico;
	}

	public void setRechn_ico(int rechn_ico) {
		this.rechn_ico = rechn_ico;
	}

	public int getRechn_dic() {
		return rechn_dic;
	}

	public void setRechn_dic(int rechn_dic) {
		this.rechn_dic = rechn_dic;
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
