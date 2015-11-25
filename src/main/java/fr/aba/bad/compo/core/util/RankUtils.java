package fr.aba.bad.compo.core.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import fr.aba.bad.compo.core.domain.rank.Rank;

public class RankUtils {
	public static final String RANKS_RE = Arrays.stream(Rank.values()).map(Enum::name).collect(Collectors.joining("|"));

}
