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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="student")
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_index")
	private Long student_index;

	@NotBlank(message="Bitte tragen Sie Student_Nachname ein.")
	@Column(name="student_nachname")
	private String student_nachname;
	
	@NotBlank(message="Bitte tragen Sie Student_Vorname ein.")
	@Column(name="student_vorname")
	private String student_vorname;
	
	@Column(name="student_sortierung")
	private String student_sortierung;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="student_gebdat")
	private Date student_gebdat;
	
	@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
	@Column(name="student_ersttermin")
	private Date student_ersttermin;
	
	@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
	@Column(name="student_letztermin")
	private Date student_letztermin;
	
	@Digits(integer=3, fraction=2)
	@Column(name="student_preis45")
	private double student_preis45;
	
	@Column(name="student_preis60")
	@Digits(integer=3, fraction=2)
	private double student_preis60;
	
	@Column(name="student_preis90")
	@Digits(integer=3, fraction=2)
	private double student_preis90;

	@Column(name="student_preis120")
	@Digits(integer=3, fraction=2)
	private double student_preis120;
	
	@Column(name="student_abrechnung")
	private int student_abrechnung;
	
	@Column(name="student_kredit")
	@Digits(integer=3, fraction=2)
	private double student_kredit;

	@Column(name="student_aktiv")
	private int student_aktiv;
	
	@Column(name="student_quelle")
	private String student_quelle;
	
	@Column(name="student_komm")
	private String student_komm;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="created_at", updatable= false)
	private Date created_At;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="updated_at")
	private Date updated_At;
	
	//OneToOne with Agentur
	@ManyToOne(fetch = FetchType.LAZY,cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="student_agentur", nullable = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "agentur_index")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("agentur_index")
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
	
	
	public List<Rechnung> getRechnungs() {
		return rechnungs;
	}

	public void setRechnungs(List<Rechnung> rechnungs) {
		this.rechnungs = rechnungs;
	}
	
	public List<Zahlung> getZahlungs() {
		return zahlungs;
	}

	public void setZahlungs(List<Zahlung> zahlungs) {
		this.zahlungs = zahlungs;
	}

	public List<Lektion> getLektions() {
		return lektions;
	}

	public void setLektions(List<Lektion> lektions) {
		this.lektions = lektions;
	}
	
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();			
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();	
	}
	
	public Agentur getAgentur() {
		return agentur;
	}

	public void setAgentur(Agentur agentur) {
		this.agentur = agentur;
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
	
	public static Student fromId(Long student_index) {
		
		if (student_index == null) {
			return null;
		}
		
		Student student = new Student();
		student.student_index = student_index;
	    return student;
	}
	
	@JsonProperty("agentur_index")
    public void setAgenturById(Long agentur_index) {
        agentur = Agentur.fromId(agentur_index);
    }
	
}
