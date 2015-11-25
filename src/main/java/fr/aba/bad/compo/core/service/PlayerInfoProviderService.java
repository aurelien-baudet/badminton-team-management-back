package fr.aba.bad.compo.core.service;

import fr.aba.bad.compo.core.domain.player.info.CivilInformation;
import fr.aba.bad.compo.core.exception.player.provider.PlayerProviderException;

public interface PlayerInfoProviderService {
	public CivilInformation getCivilInformation(String licence) throws PlayerProviderException;
}