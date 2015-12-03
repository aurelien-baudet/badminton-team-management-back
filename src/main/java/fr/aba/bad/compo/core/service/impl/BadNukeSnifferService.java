package fr.aba.bad.compo.core.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.aba.bad.compo.core.domain.player.Player;
import fr.aba.bad.compo.core.domain.team.Club;
import fr.aba.bad.compo.core.exception.club.provider.ClubNotFoundForPlayer;
import fr.aba.bad.compo.core.exception.club.provider.ClubProviderException;
import fr.aba.bad.compo.core.exception.service.impl.BadNukeException;
import fr.aba.bad.compo.core.service.ClubProviderService;

@Service
public class BadNukeSnifferService implements ClubProviderService {
	private static final Logger LOG = LoggerFactory.getLogger(BadNukeSnifferService.class);
	
	private static final String BADNUKE_SEARCH_PLAYER_URL = "http://www.badnuke.com/search/licenceresults?lic={licence}";
	private static final String BADNUKE_SEARCH_CLUB_URL = "http://www.badnuke.com/search/licences?cl={clubId}";
	private static final String CLUB_LINK_SELECTOR = "h4 a[href^=licences?cl=]";
	private static final Pattern CLUB_LINK_ID_PATTERN = Pattern.compile("licences\\?cl=(.+)");
	private static final String CLUB_NAME_SELECTOR = "h1";
	private static final String CLUB_ACRONYM_SELECTOR = "h3";
	private static final String CLUB_TOWN_SELECTOR = "h4";
	private static final String CLUB_SITE_SELECTOR = "h4 + p a";
	
	@Override
	public Club getClub(Player player) throws ClubProviderException {
		return getClub(player.getBadInfo().getLicence());
	}
	
	@Override
	public Club getClub(String licence) throws ClubProviderException {
		try {
			Document html = Jsoup.parse(getPageContent(BADNUKE_SEARCH_PLAYER_URL, licence));
			Elements clubLinks = html.select(CLUB_LINK_SELECTOR);
			if(clubLinks.size()>0) {
				String clubId = getClubId(clubLinks.get(0).attr("href"));
				Document clubHtml = Jsoup.parse(getPageContent(BADNUKE_SEARCH_CLUB_URL, clubId));
				String clubName = clubHtml.select(CLUB_NAME_SELECTOR).get(0).html();
				String clubAcronym = clubHtml.select(CLUB_ACRONYM_SELECTOR).get(0).html();
				String town = clubHtml.select(CLUB_TOWN_SELECTOR).get(0).html();
				Club club = new Club(clubName, clubAcronym, town);
				Elements siteElements = clubHtml.select(CLUB_SITE_SELECTOR);
				if(siteElements.size()>0) {
					try {
						club.setSiteUrl(new URL(siteElements.get(0).html()));
					} catch (MalformedURLException e) {
						LOG.error("Invalid site URL ("+siteElements.get(0).html()+")", e);
					}
				}
				return club;
			} else {
				throw new ClubNotFoundForPlayer("No club found for player licence "+licence+" on BadNuke");
			}
		} catch (BadNukeException e) {
			LOG.error("Failed to get club from player licence on BadNuke", e);
			throw new ClubProviderException("Failed to get club from player licence "+licence, e);
		}
	}

	private String getPageContent(String url, Object... urlVariables) throws BadNukeException {
		ResponseEntity<String> response = new RestTemplate().getForEntity(url, String.class, urlVariables);
		if(response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		} else {
			throw new BadNukeException("Failed to load BadNuke page");
		}
	}
	
	private String getClubId(String url) {
		return CLUB_LINK_ID_PATTERN.matcher(url).replaceAll("$1");
	}
}
