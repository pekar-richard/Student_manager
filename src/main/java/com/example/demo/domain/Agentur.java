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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="agentur")
public class Agentur {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="agentur_index")
	private Long agenturIndex;
	
	@NotBlank(message="Bitte tragen Sie Agentur_Kurzname ein.")
	@Column(name="agentur_kurzname")
	private String agenturKurzname;
	
	@Column(name="agentur_komm")
	private String agenturKomm;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="created_at", updatable= false)
	private Date createdAt;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="updated_at")
	private Date updatedAt;
	
	//OneToMany with Student
	@OneToMany(fetch = FetchType.LAZY, mappedBy="agentur",  cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JsonIgnore
	private List<Student> students;
	
	//OneToMany with Lektion
	@OneToMany(fetch = FetchType.LAZY, mappedBy="agentur",  cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JsonIgnore
	private List<Lektion> lektions;
	
	//OneToMany with Rechnung
	@OneToMany(fetch = FetchType.LAZY, mappedBy="agentur",  cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JsonIgnore
	private List<Rechnung> rechnungs;
	
	public void addRechnung(Rechnung tempRechnung) {
		
		if (rechnungs == null) {
			rechnungs = new ArrayList<>();
		}
		
		rechnungs.add(tempRechnung);
		
		tempRechnung.setAgentur(this);
	}

	public void addLektion(Lektion tempLektion) {
		
		if (lektions == null) {
			lektions = new ArrayList<>();
		}
		
		lektions.add(tempLektion);
		
		tempLektion.setAgentur(this);
	}
	
	public void addStudent(Student tempStudent) {
		
		if (students == null) {
			students = new ArrayList<>();
		}
		
		students.add(tempStudent);
		
		tempStudent.setAgentur(this);
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();			
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();	
	}

	public Agentur() {
		
	}
	
	
	public Long getAgenturIndex() {
		return agenturIndex;
	}

	public void setAgenturIndex(Long agenturIndex) {
		this.agenturIndex = agenturIndex;
	}

	public String getAgenturKurzname() {
		return agenturKurzname;
	}

	public void setAgenturKurzname(String agenturKurzname) {
		this.agenturKurzname = agenturKurzname;
	}

	public String getAgenturKomm() {
		return agenturKomm;
	}

	public void setAgenturKomm(String agenturKomm) {
		this.agenturKomm = agenturKomm;
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Lektion> getLektions() {
		return lektions;
	}

	public void setLektions(List<Lektion> lektions) {
		this.lektions = lektions;
	}

	public List<Rechnung> getRechnungs() {
		return rechnungs;
	}

	public void setRechnungs(List<Rechnung> rechnungs) {
		this.rechnungs = rechnungs;
	}

	public static Agentur fromId(Long agenturIndex) {
		
		if (agenturIndex == null) {
			return null;
		}
		
		Agentur agentur = new Agentur();
		agentur.agenturIndex = agenturIndex;
	    return agentur;
	}

}