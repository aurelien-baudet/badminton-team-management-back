package fr.aba.bad.compo.service;

import fr.aba.bad.compo.exception.ranking.RankingException;

public interface RankingUpdaterService {
	public void updateAllRankings() throws RankingException;
}
