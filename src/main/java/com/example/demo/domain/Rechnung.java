package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rechnung")
public class Rechnung {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rechn_index")
	private Long rechn_index;
	
	@Column(name="rechn_typ")
	private int rechn_typ;
	
	@Column(name="rechn_kunde")
	private int rechn_kunde;
	
	@Column(name="rechn_name")
	private String rechn_name;
	
	@Column(name="rechn_zusatz")
	private String rechn_zusatz;
	
	@Column(name="rechn_str")
	private String rechn_str;
	
	@Column(name="rechn_plz")
	private String rechn_plz;
	
	@Column(name="rechn_ort")
	private String rechn_ort;
	
	@Column(name="rechn_land")
	private String rechn_land;
	
	@Column(name="rechn_ico")
	private int rechn_ico;
	
	@Column(name="rechn_dic")
	private int rechn_dic;

}
