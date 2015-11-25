package fr.aba.bad.compo.core.domain.match;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Match {
	@Id
	@GeneratedValue
	private Long id;
	
	// TODO: player vs oppenent or players vs opponents
	
	@OneToMany
	private List<SetScore> scores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SetScore> getScores() {
		return scores;
	}

	public void setScores(List<SetScore> scores) {
		this.scores = scores;
	}
	
	
}
