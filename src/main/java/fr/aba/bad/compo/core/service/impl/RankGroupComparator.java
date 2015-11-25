package fr.aba.bad.compo.core.service.impl;

import java.util.Comparator;

import fr.aba.bad.compo.core.domain.rank.RankGroup;

public class RankGroupComparator implements Comparator<RankGroup> {

	@Override
	public int compare(RankGroup o1, RankGroup o2) {
		int sum1 = o1.getRanks().stream().mapToInt(r -> r.getValue()).sum();
		int sum2 = o2.getRanks().stream().mapToInt(r -> r.getValue()).sum();
		return sum1==sum2 ? 0 : (sum1>sum2 ? 1 : -1);
	}

}
