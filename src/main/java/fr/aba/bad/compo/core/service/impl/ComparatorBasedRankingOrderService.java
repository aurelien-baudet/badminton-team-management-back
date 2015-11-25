package fr.aba.bad.compo.core.service.impl;

import org.springframework.stereotype.Service;

import fr.aba.bad.compo.core.domain.rank.Rank;
import fr.aba.bad.compo.core.domain.rank.RankGroup;
import fr.aba.bad.compo.core.service.RankingOrderService;

@Service
public class ComparatorBasedRankingOrderService implements RankingOrderService {

	@Override
	public boolean isGreater(RankGroup greater, RankGroup lower) {
		return new RankGroupComparator().compare(greater, lower)>=0;
	}

	@Override
	public boolean isGreater(Rank greater, Rank lower) {
		return isGreater(new RankGroup(greater), new RankGroup(lower));
	}

}
