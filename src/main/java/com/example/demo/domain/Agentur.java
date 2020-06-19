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
	private Long agentur_index;
	
	@NotBlank(message="Bitte tragen Sie Agentur_Kurzname ein.")
	@Column(name="agentur_kurzname")
	private String agentur_kurzname;
	
	@Column(name="agentur_komm")
	private String agentur_komm;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="created_at", updatable= false)
	private Date created_At;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="updated_at")
	private Date updated_At;
	
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
	
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();			
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();	
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


	public Agentur() {
		
	}

	public Long getAgentur_index() {
		return agentur_index;
	}

	public void setAgentur_index(Long agentur_index) {
		this.agentur_index = agentur_index;
	}

	public String getAgentur_kurzname() {
		return agentur_kurzname;
	}

	public void setAgentur_kurzname(String agentur_kurzname) {
		this.agentur_kurzname = agentur_kurzname;
	}

	public String getAgentur_komm() {
		return agentur_komm;
	}

	public void setAgentur_komm(String agentur_komm) {
		this.agentur_komm = agentur_komm;
	}
	
	public static Agentur fromId(Long agentur_index) {
		
		if (agentur_index == null) {
			return null;
		}
		
		Agentur agentur = new Agentur();
		agentur.agentur_index = agentur_index;
	    return agentur;
	}

}