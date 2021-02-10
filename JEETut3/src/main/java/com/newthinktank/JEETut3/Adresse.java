package com.newthinktank.JEETut3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adresse")
public class Adresse {

	public Adresse() {
	}

	public Adresse(String strasse, String hausnummer, int plz) {
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.plz = plz;
	}

    @Id
    @GeneratedValue
    @Column(name = "adressid")
    private int id;

    @Column(name = "strasse")
    private String strasse;

    @Column(name = "hausnummer")
    private String hausnummer;

    @Column(name = "plz")
    private int plz;

	public String getStraße() {
		// TODO Auto-generated method stub
		return strasse;
	}

	public String getHausnummer() {
		// TODO Auto-generated method stub
		return hausnummer;
	}

}
