package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="agentur")
public class Agentur {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="agentur_index")
	private Long agentur_index;
	
	@Column(name="agentur_kurzname")
	private String agentur_kurzname;
	
	@Column(name="agentur_komm", unique = true)
	private String agentur_komm;
	
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

}