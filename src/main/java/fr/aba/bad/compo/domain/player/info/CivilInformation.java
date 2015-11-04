package fr.aba.bad.compo.domain.player.info;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class CivilInformation {
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column
	private LocalDate birthDate;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	
	public CivilInformation firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public CivilInformation lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public CivilInformation gender(Gender gender) {
		this.gender = gender;
		return this;
	}
	
	public CivilInformation birthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
		return this;
	}
}
