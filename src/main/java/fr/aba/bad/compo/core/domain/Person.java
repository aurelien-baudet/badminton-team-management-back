package fr.aba.bad.compo.core.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import fr.aba.bad.compo.core.domain.player.info.CivilInformation;

@Entity
public class Person {
	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private Contact contactInformation;
	
	@Embedded
	private CivilInformation civilInformation;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contact getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(Contact contactInformation) {
		this.contactInformation = contactInformation;
	}

	public CivilInformation getCivilInformation() {
		return civilInformation;
	}

	public void setCivilInformation(CivilInformation civilInformation) {
		this.civilInformation = civilInformation;
	}

	
	public Person id(Long id) {
		this.id = id;
		return this;
	}
	
	public Person contactInformation(Contact contactInformation) {
		this.contactInformation = contactInformation;
		return this;
	}
	
	public Person civilInformation(CivilInformation civilInformation) {
		setCivilInformation(civilInformation);
		return this;
	}

}
