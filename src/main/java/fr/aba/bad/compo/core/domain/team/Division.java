package fr.aba.bad.compo.core.domain.team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import fr.aba.bad.compo.core.domain.Person;

@Entity
public class Division {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@OneToOne
	private Person supervisor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Person getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Person supervisor) {
		this.supervisor = supervisor;
	}
	
	
	
}
