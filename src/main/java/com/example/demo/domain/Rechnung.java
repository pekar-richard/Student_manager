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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="rechnung")
public class Rechnung {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rechn_index")
	private Long rechnIndex;
	
	@Column(name="rechn_typ")
	private int rechnTyp;

	@NotBlank(message="Bitte tragen Sie Rechn_Name ein.")
	@Column(name="rechn_name")
	private String rechnName;
	
	@NotBlank(message="Bitte tragen Sie Rechn_Zusatz. ein.")
	@Column(name="rechn_zusatz")
	private String rechnZusatz;
	
	@NotBlank(message="Bitte tragen Sie Rechn_Str. ein.")
	@Column(name="rechn_str")
	private String rechnStr;
	
	@NotBlank(message="Bitte tragen Sie Rechn_PLZ ein.")
	@Column(name="rechn_plz")
	private String rechnPlz;
	
	@NotBlank(message="Bitte tragen Sie Rechn_Ort ein.")
	@Column(name="rechn_ort")
	private String rechnOrt;
	
	@NotBlank(message="Bitte tragen Sie Rechn_Land ein.")
	@Column(name="rechn_land")
	private String rechnLand;
	
	@Column(name="rechn_ico")
	private int rechnIco;
	
	@Column(name="rechn_dic")
	private int rechnDic;

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="created_at", updatable= false)
	private Date createdAt;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="updated_at")
	private Date updatedAt;
	
	//ManytoOne with Agentur
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="rechn_agentur", nullable = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "agenturIndex")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("agenturIndex")
	private Agentur agentur;
	
	//ManytoOne with Student
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="rechn_student", nullable = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "studentIndex")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("studentIndex")
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
	
	public static Rechnung fromId(Long rechnung_index) {
		
		if (rechnung_index == null) {
			return null;
		}
		Rechnung rechnung = new Rechnung();
		rechnung.rechnIndex = rechnung_index;
	    return rechnung;
	}
	
	
	public Long getRechnIndex() {
		return rechnIndex;
	}

	public void setRechnIndex(Long rechnIndex) {
		this.rechnIndex = rechnIndex;
	}

	public int getRechnTyp() {
		return rechnTyp;
	}

	public void setRechnTyp(int rechnTyp) {
		this.rechnTyp = rechnTyp;
	}

	public String getRechnName() {
		return rechnName;
	}

	public void setRechnName(String rechnName) {
		this.rechnName = rechnName;
	}

	public String getRechnZusatz() {
		return rechnZusatz;
	}

	public void setRechnZusatz(String rechnZusatz) {
		this.rechnZusatz = rechnZusatz;
	}

	public String getRechnStr() {
		return rechnStr;
	}

	public void setRechnStr(String rechnStr) {
		this.rechnStr = rechnStr;
	}

	public String getRechnPlz() {
		return rechnPlz;
	}

	public void setRechnPlz(String rechnPlz) {
		this.rechnPlz = rechnPlz;
	}

	public String getRechnOrt() {
		return rechnOrt;
	}

	public void setRechnOrt(String rechnOrt) {
		this.rechnOrt = rechnOrt;
	}

	public String getRechnLand() {
		return rechnLand;
	}

	public void setRechnLand(String rechnLand) {
		this.rechnLand = rechnLand;
	}

	public int getRechnIco() {
		return rechnIco;
	}

	public void setRechnIco(int rechnIco) {
		this.rechnIco = rechnIco;
	}

	public int getRechnDic() {
		return rechnDic;
	}

	public void setRechnDic(int rechnDic) {
		this.rechnDic = rechnDic;
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
