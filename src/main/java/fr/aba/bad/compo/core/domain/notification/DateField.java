package fr.aba.bad.compo.core.domain.notification;

import java.time.temporal.Temporal;
import java.util.function.Function;

import fr.aba.bad.compo.core.domain.match.MatchDay;

public enum DateField {
	START_PERIOD_DAY(m -> m.getCalendar().getStart()),
	END_PERIOD_DAY(m -> m.getCalendar().getEnd()),
	RANK_DATE(m -> m.getCalendar().getRankDate()),
	PLAY_DATE(m -> m.getCalendar().getPlayDate());
	
	private final Function<MatchDay, Temporal> getter;

	private DateField(Function<MatchDay, Temporal> getter) {
		this.getter = getter;
	}

	public Temporal getDate(MatchDay matchDay) {
		return getter.apply(matchDay);
	}
}
