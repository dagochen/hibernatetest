package com.newthinktank.JEETut3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adresse")
public class Adresse {

	public Adresse()
	{
		
	}
	
	public Adresse(String string, String string2, int i) {
		strasse = string;
		hausnummer = string2;
		plz = i;
	}

	@Id
	@Column(name = "adressid")
	private int id;
	
	@Column(name = "strasse")
	private String strasse;
	
	@Column(name = "hausnummer")
	private String hausnummer;
	
	@Column(name = "plz")
	private int plz;
	
	
}
