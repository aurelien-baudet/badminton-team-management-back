package fr.aba.bad.compo.core.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AddressInformation {
	@Column
	private String address;
	
	@Column
	private String postalCode;
	
	@Column
	private String town;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
	
	
	public AddressInformation address(String address) {
		this.address = address;
		return this;
	}
	
	public AddressInformation postalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}
	
	public AddressInformation town(String town) {
		this.town = town;
		return this;
	}
	
}
