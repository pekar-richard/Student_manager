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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="student")
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_index")
	private Long studentIndex;

	@NotBlank(message="Bitte tragen Sie Student_Nachname ein.")
	@Column(name="student_nachname")
	private String studentNachname;
	
	@NotBlank(message="Bitte tragen Sie Student_Vorname ein.")
	@Column(name="student_vorname")
	private String studentVorname;
	
	@Column(name="student_sortierung")
	private String studentSortierung;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="student_gebdat")
	private Date studentGebdat;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="student_ersttermin")
	private Date studentErsttermin;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="student_letztermin")
	private Date studentLetztermin;
	
	@Digits(integer=3, fraction=2)
	@Column(name="student_preis45")
	private double studentPreis45;
	
	@Digits(integer=3, fraction=2)
	@Column(name="student_preis60")
	private double studentPreis60;

	@Digits(integer=3, fraction=2)
	@Column(name="student_preis90")
	private double studentPreis90;
	
	@Digits(integer=3, fraction=2)
	@Column(name="student_preis120")
	private double studentPreis120;
	
	@Column(name="student_abrechnung")
	private int studentAbrechnung;
	
	@Column(name="student_kredit")
	private double studentKredit;

	@Column(name="student_aktiv")
	private int studentAktiv;
	
	@Column(name="student_quelle")
	private String studentQuelle;
	
	@Column(name="student_komm")
	private String studentKomm;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="created_at", updatable= false)
	private Date createdAt;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="updated_at")
	private Date updatedAt;
	
	//OneToOne with Agentur
	@ManyToOne(fetch = FetchType.LAZY,cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="student_agentur", nullable = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "agenturIndex")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("agenturIndex")
	private Agentur agentur;

	//OneToMany with Lektion
	@OneToMany(fetch = FetchType.LAZY,  mappedBy="student",  cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH,CascadeType.REMOVE})
	@JsonIgnore
	private List<Lektion> lektions;
	
	//OneToMany with Zahlung
	@OneToMany(fetch = FetchType.LAZY,  mappedBy="student",  cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH,CascadeType.REMOVE})
	@JsonIgnore
	private List<Zahlung> zahlungs;
	
	//OneToMany with Rechnung
	@OneToMany(fetch = FetchType.LAZY,  mappedBy="student",  cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH,CascadeType.REMOVE})
	@JsonIgnore
	private List<Rechnung> rechnungs;
	
	
	public void removeAgentur() {	
		
		this.agentur=null;
	}
	
	public void addLektion(Lektion tempLektion) {
		
		if (lektions == null) {
			lektions = new ArrayList<>();
		}
		
		lektions.add(tempLektion);
		
		tempLektion.setStudent(this);
	}
	
	public void addZahlung(Zahlung tempZahlung) {
		
		if (zahlungs == null) {
			zahlungs = new ArrayList<>();
		}
		
		zahlungs.add(tempZahlung);
		
		tempZahlung.setStudent(this);
	}
	
	public void addRechnung(Rechnung tempRechnung) {
		
		if (rechnungs == null) {
			rechnungs = new ArrayList<>();
		}
		
		rechnungs.add(tempRechnung);
		
		tempRechnung.setStudent(this);
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

	
	public Student() {	
			
	}

	public Long getStudentIndex() {
		return studentIndex;
	}

	public void setStudentIndex(Long studentIndex) {
		this.studentIndex = studentIndex;
	}

	public String getStudentNachname() {
		return studentNachname;
	}

	public void setStudentNachname(String studentNachname) {
		this.studentNachname = studentNachname;
	}

	public String getStudentVorname() {
		return studentVorname;
	}

	public void setStudentVorname(String studentVorname) {
		this.studentVorname = studentVorname;
	}

	public String getStudentSortierung() {
		return studentSortierung;
	}

	public void setStudentSortierung(String studentSortierung) {
		this.studentSortierung = studentSortierung;
	}

	public Date getStudentGebdat() {
		return studentGebdat;
	}

	public void setStudentGebdat(Date studentGebdat) {
		this.studentGebdat = studentGebdat;
	}

	public Date getStudentErsttermin() {
		return studentErsttermin;
	}

	public void setStudentErsttermin(Date studentErsttermin) {
		this.studentErsttermin = studentErsttermin;
	}

	public Date getStudentLetztermin() {
		return studentLetztermin;
	}

	public void setStudentLetztermin(Date studentLetztermin) {
		this.studentLetztermin = studentLetztermin;
	}

	public double getStudentPreis45() {
		return studentPreis45;
	}

	public void setStudentPreis45(double studentPreis45) {
		this.studentPreis45 = studentPreis45;
	}

	public double getStudentPreis60() {
		return studentPreis60;
	}

	public void setStudentPreis60(double studentPreis60) {
		this.studentPreis60 = studentPreis60;
	}

	public double getStudentPreis90() {
		return studentPreis90;
	}

	public void setStudentPreis90(double studentPreis90) {
		this.studentPreis90 = studentPreis90;
	}

	public double getStudentPreis120() {
		return studentPreis120;
	}

	public void setStudentPreis120(double studentPreis120) {
		this.studentPreis120 = studentPreis120;
	}

	public int getStudentAbrechnung() {
		return studentAbrechnung;
	}

	public void setStudentAbrechnung(int studentAbrechnung) {
		this.studentAbrechnung = studentAbrechnung;
	}

	public double getStudentKredit() {
		return studentKredit;
	}

	public void setStudentKredit(double studentKredit) {
		this.studentKredit = studentKredit;
	}

	public String getStudentQuelle() {
		return studentQuelle;
	}

	public void setStudentQuelle(String studentQuelle) {
		this.studentQuelle = studentQuelle;
	}

	public String getStudentKomm() {
		return studentKomm;
	}

	public void setStudentKomm(String studentKomm) {
		this.studentKomm = studentKomm;
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

	public int getStudentAktiv() {
		return studentAktiv;
	}

	public void setStudentAktiv(int studentAktiv) {
		this.studentAktiv = studentAktiv;
	}

	public static Student fromId(Long student_index) {
		
		if (student_index == null) {
			return null;
		}
		
		Student student = new Student();
		student.studentIndex = student_index;
	    return student;
	}
	
	@JsonProperty("agenturIndex")
    public void setAgenturById(Long agenturIndex) {
        agentur = Agentur.fromId(agenturIndex);
    }
	
}
