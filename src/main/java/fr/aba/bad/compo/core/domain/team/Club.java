package fr.aba.bad.compo.core.domain.team;

import java.net.URL;

public class Club {
	private String name;
	
	private String acronym;

	private String town;
	
	private URL siteUrl;
	
	public Club() {
		super();
	}

	public Club(String name, String acronym, String town) {
		super();
		this.name = name;
		this.acronym = acronym;
		this.town = town;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public URL getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(URL siteUrl) {
		this.siteUrl = siteUrl;
	}
	
	public Club siteUrl(URL siteUrl) {
		this.siteUrl = siteUrl;
		return this;
	}
}
