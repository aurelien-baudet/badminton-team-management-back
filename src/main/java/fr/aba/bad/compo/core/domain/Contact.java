package fr.aba.bad.compo.core.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contact {
	@Column
	private String phoneNumber;
	
	@Column
	private String email;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public Contact phoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	
	public Contact email(String email) {
		this.email = email;
		return this;
	}
	
}
