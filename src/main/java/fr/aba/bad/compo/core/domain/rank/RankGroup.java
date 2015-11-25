package fr.aba.bad.compo.core.domain.rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class RankGroup {
	private List<Rank> ranks;

	public RankGroup(Rank... ranks) {
		this(new ArrayList<>(Arrays.asList(ranks)));
	}

	public RankGroup(List<Rank> ranks) {
		super();
		this.ranks = ranks;
	}

	public List<Rank> getRanks() {
		return ranks;
	}

	public void setRanks(List<Rank> ranks) {
		this.ranks = ranks;
	}

	@Override
	public String toString() {
		final StringJoiner joiner = new StringJoiner("/", "[", "]");
		ranks.stream().forEach(r -> joiner.add(r.name()));
		return joiner.toString();
	}
}
