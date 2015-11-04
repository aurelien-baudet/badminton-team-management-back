package fr.aba.bad.compo.service;

import java.time.LocalDate;
import java.util.List;

import fr.aba.bad.compo.domain.player.Player;
import fr.aba.bad.compo.domain.rank.Ranking;
import fr.aba.bad.compo.exception.RankingException;

public interface RankingProviderService {
	public Ranking getRanking(String licence, LocalDate rankDate) throws RankingException;
	
	public Ranking getRanking(Player player, LocalDate rankDate) throws RankingException;

	public List<Ranking> getRankings(Player player) throws RankingException;

	public List<Ranking> getRankings(String licence) throws RankingException;
}
