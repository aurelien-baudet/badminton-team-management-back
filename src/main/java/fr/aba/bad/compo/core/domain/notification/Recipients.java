package fr.aba.bad.compo.core.domain.notification;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.aba.bad.compo.core.domain.Contact;
import fr.aba.bad.compo.core.domain.Person;
import fr.aba.bad.compo.core.domain.match.MatchDay;
import fr.aba.bad.compo.core.domain.player.InternalPlayer;
import fr.aba.bad.compo.core.domain.player.Player;
import fr.aba.bad.compo.core.domain.team.InternalTeam;
import fr.aba.bad.compo.core.domain.team.OpponentTeam;

public enum Recipients {
	RECEIVER_PLAYERS(m -> m.getReceiverTeam().getPlayers().stream().map(Recipients::getContact).collect(Collectors.toList())),
	INTERNAL_TEAM_LEADER(m -> Arrays.asList(getContact(getTeam(m, InternalTeam.class).getLeader()))),
	OPPONENT_TEAM_MANAGER(m -> Arrays.asList(getContact(getTeam(m, OpponentTeam.class).getManager())));
	
	private final Function<MatchDay, List<Contact>> accessor;

	private Recipients(Function<MatchDay, List<Contact>> accessor) {
		this.accessor = accessor;
	}
	
	public List<Contact> getContacts(MatchDay matchday) {
		return accessor.apply(matchday);
	}
	
	private static <T> T getTeam(MatchDay matchday, Class<T> clazz) {
		return (T) (clazz.isAssignableFrom(matchday.getReceiverTeam().getClass()) ? matchday.getReceiverTeam() : matchday.getGuestTeam());
	}
	
	private static Contact getContact(Player p) {
		return getContact(((InternalPlayer) p).getPerson());
	}
	
	private static Contact getContact(Person p) {
		return p.getContactInformation();
	}
}
