package fr.aba.bad.compo.core.service;

import fr.aba.bad.compo.core.domain.player.Player;
import fr.aba.bad.compo.core.domain.team.Club;
import fr.aba.bad.compo.core.exception.club.provider.ClubProviderException;

public interface ClubProviderService {

	Club getClub(Player player) throws ClubProviderException;

	Club getClub(String licence) throws ClubProviderException;

}