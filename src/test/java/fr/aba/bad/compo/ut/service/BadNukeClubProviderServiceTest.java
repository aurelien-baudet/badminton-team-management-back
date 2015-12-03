package fr.aba.bad.compo.ut.service;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import fr.aba.bad.compo.core.domain.player.InternalPlayer;
import fr.aba.bad.compo.core.domain.player.info.BadInformation;
import fr.aba.bad.compo.core.domain.team.Club;
import fr.aba.bad.compo.core.exception.club.provider.ClubProviderException;
import fr.aba.bad.compo.core.exception.ranking.RankingException;
import fr.aba.bad.compo.core.service.impl.BadNukeSnifferService;

public class BadNukeClubProviderServiceTest {
	BadNukeSnifferService badnukeSnifferService;

	@Before
	public void setup() {
		badnukeSnifferService = new BadNukeSnifferService();
	}
	
	@Test
	public void playerClub() throws RankingException, MalformedURLException, ClubProviderException {
		Club club = badnukeSnifferService.getClub(new InternalPlayer().badInfo(new BadInformation().licence("6753956")));
		assertEquals("Name should be Cercle Paul Bert Badminton", "Cercle Paul Bert Badminton", club.getName());
		assertEquals("Acronym should be CPB", "CPB", club.getAcronym());
		assertEquals("Town should be Rennes", "Rennes", club.getTown());
		assertEquals("Site should be http://badcpb.fr", new URL("http://badcpb.fr"), club.getSiteUrl());
	}
	
}
