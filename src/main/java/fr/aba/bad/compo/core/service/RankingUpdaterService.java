package fr.aba.bad.compo.core.service;

import fr.aba.bad.compo.core.exception.ranking.RankingException;

public interface RankingUpdaterService {
	public void updateAllRankings() throws RankingException;
}
