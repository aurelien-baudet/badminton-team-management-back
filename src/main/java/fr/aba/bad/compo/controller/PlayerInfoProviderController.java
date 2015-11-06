package fr.aba.bad.compo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.aba.bad.compo.domain.player.info.CivilInformation;
import fr.aba.bad.compo.exception.player.provider.PlayerInfoNotFoundException;
import fr.aba.bad.compo.exception.player.provider.PlayerProviderException;
import fr.aba.bad.compo.service.PlayerInfoProviderService;

@RestController
@RequestMapping("players")
public class PlayerInfoProviderController {
	private static final Logger LOG = LoggerFactory.getLogger(PlayerInfoProviderController.class);
	
	@Autowired
	PlayerInfoProviderService playerInfoProviderService;
	
	@RequestMapping(value="{licence}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public CivilInformation getInfo(@PathVariable String licence) throws PlayerProviderException {
		return playerInfoProviderService.getCivilInformation(licence);
	}
	
	@RequestMapping(value="{licence}", method=RequestMethod.GET, params="emptyIfInfoNotFound=true")
	@ResponseStatus(HttpStatus.OK)
	public CivilInformation getInfoWithDefault(@PathVariable String licence) throws PlayerProviderException {
		try {
			return playerInfoProviderService.getCivilInformation(licence);
		} catch (PlayerInfoNotFoundException e) {
			LOG.debug("Failed to get player information. Using default provided value", e);
			return null;
		}
	}
}
