package fr.aba.bad.compo.domain.team;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import fr.aba.bad.compo.domain.Person;

@Entity
public class OpponentTeam extends Team {
	@OneToOne
	private Person manager;

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}
	
	
}
