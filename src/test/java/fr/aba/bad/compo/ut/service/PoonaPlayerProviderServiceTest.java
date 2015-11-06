package fr.aba.bad.compo.ut.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.aba.bad.compo.BadCompoApplication;
import fr.aba.bad.compo.domain.player.info.CivilInformation;
import fr.aba.bad.compo.exception.player.provider.PlayerProviderException;
import fr.aba.bad.compo.service.impl.PoonaSnifferService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BadCompoApplication.class)
public class PoonaPlayerProviderServiceTest {
	@Autowired
	PoonaSnifferService playerProviderService;
	
	@Test
	public void playerInfo() throws PlayerProviderException {
		CivilInformation info = playerProviderService.getCivilInformation("6753956");
		assertEquals("Should be Aurelien", "Aurelien", info.getFirstName());
		assertEquals("Should be BAUDET", "BAUDET", info.getLastName());
	}
}
