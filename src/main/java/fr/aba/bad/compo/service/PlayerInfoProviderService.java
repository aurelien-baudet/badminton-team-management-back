package fr.aba.bad.compo.service;

import fr.aba.bad.compo.domain.player.info.CivilInformation;
import fr.aba.bad.compo.exception.PlayerProviderException;

public interface PlayerInfoProviderService {
	public CivilInformation getCivilInformation(String licence) throws PlayerProviderException;
}