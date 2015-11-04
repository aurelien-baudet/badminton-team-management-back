package fr.aba.bad.compo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.aba.bad.compo.domain.player.info.CivilInformation;
import fr.aba.bad.compo.exception.PlayerProviderException;
import fr.aba.bad.compo.service.PlayerInfoProviderService;

@RestController
@RequestMapping("players")
public class PlayerInfoProviderController {
	@Autowired
	PlayerInfoProviderService playerInfoProviderService;
	
	@RequestMapping(value="{licence}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public CivilInformation getInfo(@PathVariable String licence) throws PlayerProviderException {
		return playerInfoProviderService.getCivilInformation(licence);
	}
}
