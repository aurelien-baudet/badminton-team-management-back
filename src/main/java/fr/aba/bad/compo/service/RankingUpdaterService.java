package fr.aba.bad.compo.service;

import fr.aba.bad.compo.exception.RankingException;

public interface RankingUpdaterService {
	public void updateAllRankings() throws RankingException;
}
