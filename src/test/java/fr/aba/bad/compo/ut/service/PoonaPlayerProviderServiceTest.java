package fr.aba.bad.compo.ut.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.aba.bad.compo.core.domain.player.info.CivilInformation;
import fr.aba.bad.compo.core.exception.player.provider.PlayerProviderException;
import fr.aba.bad.compo.core.service.impl.PoonaSnifferService;

public class PoonaPlayerProviderServiceTest {
	PoonaSnifferService playerProviderService;
	
	@Before
	public void setup() {
		playerProviderService = new PoonaSnifferService();
	}
	
	@Test
	public void playerInfo() throws PlayerProviderException {
		CivilInformation info = playerProviderService.getCivilInformation("6753956");
		assertEquals("Should be Aurelien", "Aurelien", info.getFirstName());
		assertEquals("Should be BAUDET", "BAUDET", info.getLastName());
	}
}
