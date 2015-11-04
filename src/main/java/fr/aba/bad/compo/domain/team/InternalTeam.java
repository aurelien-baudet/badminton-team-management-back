package fr.aba.bad.compo.domain.team;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import fr.aba.bad.compo.domain.player.Player;

@Entity
public class InternalTeam extends Team {
	@OneToOne
	private Player leader;

	public Player getLeader() {
		return leader;
	}

	public void setLeader(Player leader) {
		this.leader = leader;
	}
	
	
}
