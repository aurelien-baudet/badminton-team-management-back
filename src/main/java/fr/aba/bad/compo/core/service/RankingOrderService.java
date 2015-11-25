package fr.aba.bad.compo.core.service;

import fr.aba.bad.compo.core.domain.rank.Rank;
import fr.aba.bad.compo.core.domain.rank.RankGroup;

public interface RankingOrderService {
	public boolean isGreater(RankGroup greater, RankGroup lower);

	public boolean isGreater(Rank greater, Rank lower);
}
