package fr.aba.bad.compo.core.domain.player;

import fr.aba.bad.compo.core.domain.AddressInformation;
import fr.aba.bad.compo.core.domain.Person;

public class InternalPlayer extends Player {
	private Person person;
	
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
