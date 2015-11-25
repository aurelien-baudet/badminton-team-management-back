package fr.aba.bad.compo.core.service;

import java.time.LocalDate;
import java.util.List;

import fr.aba.bad.compo.core.domain.player.Player;
import fr.aba.bad.compo.core.domain.rank.Ranking;
import fr.aba.bad.compo.core.exception.ranking.RankingException;

public interface RankingProviderService {
	public Ranking getRanking(String licence, LocalDate rankDate) throws RankingException;
	
	public Ranking getRanking(Player player, LocalDate rankDate) throws RankingException;

	public List<Ranking> getRankings(Player player) throws RankingException;

	public List<Ranking> getRankings(String licence) throws RankingException;
}
