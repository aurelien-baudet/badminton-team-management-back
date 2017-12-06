package fr.aba.bad.compo.core.domain.player.info;

import java.util.ArrayList;
import java.util.List;

import fr.aba.bad.compo.core.domain.rank.Ranking;

public class BadInformation {
	private String licence;
	
	private List<Ranking> rankings = new ArrayList<>();

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public List<Ranking> getRankings() {
		return rankings;
	}

	public void setRankings(List<Ranking> rankings) {
		this.rankings = rankings;
	}
	
	public BadInformation licence(String licence) {
		setLicence(licence);
		return this;
	}
	
	public BadInformation rankings(List<Ranking> rankings) {
		setRankings(rankings);
		return this;
	}
	
	public BadInformation ranking(Ranking ranking) {
		rankings.add(ranking);
		return this;
	}
	
}
