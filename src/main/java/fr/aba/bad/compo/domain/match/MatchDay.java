package fr.aba.bad.compo.domain.match;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import fr.aba.bad.compo.domain.team.Team;

@Entity
public class MatchDay {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private Integer number;
	
	@Embedded
	private Calendar calendar;
	
	@OneToOne
	private Team receiverTeam;

	@OneToOne
	private Team guestTeam;
	
	@OneToMany
	private List<Match> matches;

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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Team getReceiverTeam() {
		return receiverTeam;
	}

	public void setReceiverTeam(Team receiverTeam) {
		this.receiverTeam = receiverTeam;
	}

	public Team getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(Team guestTeam) {
		this.guestTeam = guestTeam;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	
	
}
