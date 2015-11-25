package fr.aba.bad.compo.core.domain.player;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import fr.aba.bad.compo.core.domain.AddressInformation;
import fr.aba.bad.compo.core.domain.Person;

@Entity
public class InternalPlayer extends Player {
	@OneToOne
	private Person person;
	
	@Embedded
	private AddressInformation addressInformation;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public AddressInformation getAddressInformation() {
		return addressInformation;
	}

	public void setAddressInformation(AddressInformation addressInformation) {
		this.addressInformation = addressInformation;
	}
	
	
	public InternalPlayer person(Person person) {
		this.person = person;
		return this;
	}
	
	public InternalPlayer addressInformation(AddressInformation addressInformation) {
		setAddressInformation(addressInformation);
		return this;
	}

	@Override
	public String toString() {
		return person.getCivilInformation().getFirstName()+" "+person.getCivilInformation().getLastName()+" ("+getBadInfo().getLicence()+")";
	}
	
	
}
