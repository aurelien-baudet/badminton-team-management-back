package fr.aba.bad.compo.domain.team;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import fr.aba.bad.compo.domain.player.Player;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use=JsonTypeInfo.Id.MINIMAL_CLASS, include=As.PROPERTY, property="@type")
public abstract class Team {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@OneToOne
	private Division division;
	
	@OneToMany(cascade= {CascadeType.DETACH, CascadeType.REFRESH})
	private Set<Player> players;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	
	
}
