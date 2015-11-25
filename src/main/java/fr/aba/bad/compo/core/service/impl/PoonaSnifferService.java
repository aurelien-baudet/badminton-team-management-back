package fr.aba.bad.compo.core.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import fr.aba.bad.compo.core.domain.player.Player;
import fr.aba.bad.compo.core.domain.player.info.CivilInformation;
import fr.aba.bad.compo.core.domain.rank.Rank;
import fr.aba.bad.compo.core.domain.rank.Ranking;
import fr.aba.bad.compo.core.exception.player.provider.PlayerInfoNotFoundException;
import fr.aba.bad.compo.core.exception.player.provider.PlayerNotFoundException;
import fr.aba.bad.compo.core.exception.player.provider.PlayerProviderException;
import fr.aba.bad.compo.core.exception.ranking.RankingDateException;
import fr.aba.bad.compo.core.exception.ranking.RankingException;
import fr.aba.bad.compo.core.exception.service.impl.PoonaException;
import fr.aba.bad.compo.core.service.PlayerInfoProviderService;
import fr.aba.bad.compo.core.service.RankingProviderService;
import fr.aba.bad.compo.core.util.RankUtils;

@Service
public class PoonaSnifferService implements RankingProviderService, PlayerInfoProviderService {

	private static final Logger LOG = LoggerFactory.getLogger(PoonaSnifferService.class);
	
	@SuppressWarnings("serial")
	private static final MultiValueMap<String, String> FORM_PARAMS = new LinkedMultiValueMap<String, String>() {{
		add("Action", "consultation_joueur_rechercher");
		add("requestForm", "formRechercher");
	}};
	@SuppressWarnings("serial")
	private static final Map<String, BiConsumer<Ranking, Rank>> SETTERS = new HashMap<String, BiConsumer<Ranking, Rank>>() {{
		put("Simple Homme", Ranking::setSingles);
		put("Simple Dame", Ranking::setSingles);
		put("Double Homme", Ranking::setDoubles);
		put("Double Dame", Ranking::setDoubles);
		put("Double Mixte", Ranking::setMixedDoubles);
	}};
	private static final String POONA_RANKING_URL = "http://poona.ffbad.org/page.php?P=fo/menu/public/accueil/classement_hebdo";
	private static final String LICENCE_PARAM = "recherche_text_licence";
	private static final String DATE_SELECTION_PARAM = "recherche_select_classementHebdo";
	private static final String RANKING_SECTION_SELECTOR = "fieldset";
	private static final String RANKING_TYPE_SELECTOR = "legend";
	private static final String RANKING_CONTAINER_SELECTOR = ".boxConsultationClassement";
	private static final Pattern RANKING_PATTERN = Pattern.compile(".*Classement\\s*:\\s*("+RankUtils.RANKS_RE+").*", Pattern.MULTILINE | Pattern.DOTALL);
	private static final Pattern RANKING_DATE_PATTERN = Pattern.compile(".*Date du classement\\s*:\\s*([0-9]{2}-[0-9]{2}-[0-9]{4}).*", Pattern.MULTILINE | Pattern.DOTALL);
	private static final String SELECT_DATE_SELECTOR = "#recherche_select_classementHebdo option";
	private static final String PLAYER_INFO_SELECTOR = ".blocNom";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static final String NO_INFO_TEXT = "Fiche non disponible";
	private static final String NO_INFO_SELECTOR = ".boxAvertissement .boxContenu";

	
	@Override
	public List<Ranking> getRankings(Player player) throws RankingException {
		return getRankings(player.getBadInfo().getLicence());
	}

	@Override
	public Ranking getRanking(Player player, LocalDate rankDate) throws RankingException {
		return getRanking(player.getBadInfo().getLicence(), rankDate);
	}

	@Override
	public List<Ranking> getRankings(String licence) throws RankingException {
		Elements options = getDateOptions(licence);
		List<Ranking> ranking = new ArrayList<>(options.size());
		for(Element option : options) {
			ranking.add(getRanking(getParams(licence, option)));
		}
		return ranking;
	}

	@Override
	public Ranking getRanking(String licence, LocalDate rankDate) throws RankingException {
		LOG.info("Get the ranking for player {} for date {} through Poona", licence, rankDate);
		return getRanking(getParams(licence, rankDate));
	}


	@Override
	public CivilInformation getCivilInformation(String licence) throws PlayerProviderException {
		try {
			Document html = Jsoup.parse(getPageContent(getParams(licence)));
			Elements nameElements = html.select(PLAYER_INFO_SELECTOR);
			if(!nameElements.isEmpty()) {
				String[] fullNameParts = nameElements.get(0).text().split(" ");
				StringJoiner firstName = new StringJoiner(" ");
				StringJoiner lastName = new StringJoiner(" ");
				for(String part : fullNameParts) {
					if(StringUtils.isAllUpperCase(part)) {
						lastName.add(part);
					} else {
						firstName.add(part);
					}
				}
				return new CivilInformation().firstName(firstName.toString()).lastName(lastName.toString());
			} else {
				if(existsButNoInfo(html)) {
					throw new PlayerInfoNotFoundException("Player information not found for "+licence, licence);
				} else {
					throw new PlayerNotFoundException("Player with "+licence+" not found", licence);
				}
			}
		} catch (PoonaException e) {
			throw new PlayerProviderException("Failed to get player information ("+licence+")", e);
		}
	}
	
	private String getPageContent(MultiValueMap<String, String> params) throws PoonaException {
		ResponseEntity<String> response = new RestTemplate().postForEntity(POONA_RANKING_URL, params, String.class);
		if(response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		} else {
			throw new PoonaException("Failed to load Poona page");
		}
	}

	
	private Ranking getRanking(MultiValueMap<String, String> params) throws RankingException {
		try {
			Ranking ranking = parseRanking(getPageContent(params));
			LOG.info("Generated ranking {}", ranking);
			return ranking;
		} catch (PoonaException e) {
			throw new RankingException("Failed to get ranking for "+params.get(LICENCE_PARAM), e);
		}
	}

	private Ranking parseRanking(String body) throws RankingException {
		Ranking ranking = new Ranking();
		Document html = Jsoup.parse(body);
		Elements fieldsets = html.select(RANKING_SECTION_SELECTOR);
		if(fieldsets.isEmpty()) {
			if(existsButNoInfo(html)) {
				// if no information but there is a response, assume that player is not ranked for now
				LOG.info("There is no ranking information => using default ranking");
				ranking.singles(Rank.NC).doubles(Rank.NC).mixedDoubles(Rank.NC);
			} else {
				// nothing found at all => error on provided player licence (doesn't exist)
				throw new RankingException("Can't get ranking for player. It may be due to invalid licence");
			}
		} else {
			for(Element element : fieldsets) {
				String type = element.select(RANKING_TYPE_SELECTOR).html();
				String content = element.select(RANKING_CONTAINER_SELECTOR).html();
				String rank = RANKING_PATTERN.matcher(content).replaceAll("$1");
				LocalDate date = LocalDate.parse(RANKING_DATE_PATTERN.matcher(content).replaceAll("$1"), DATE_FORMATTER);
				SETTERS.get(type).accept(ranking, Rank.valueOf(rank));
				ranking.setDate(date);
			}
		}
		return ranking;
	}

	private boolean existsButNoInfo(Document html) {
		Elements errorMsgBox = html.select(NO_INFO_SELECTOR);
		return !errorMsgBox.isEmpty() && errorMsgBox.text().contains(NO_INFO_TEXT);
	}
	
	private MultiValueMap<String, String> getParams(String licence, LocalDate rankDate) throws RankingException {
		Elements options = getDateOptions(licence);
		Optional<Element> element = options.stream().
											filter(e -> e.html().equals(rankDate.format(DATE_FORMATTER))).
											findFirst();
		element.orElseThrow(() -> new RankingDateException("The provided date doesn't exist on Poona for now"));
		return getParams(licence, element.get());
	}

	private MultiValueMap<String, String> getParams(String licence, Element option) {
		MultiValueMap<String, String> map = getParams(licence);
		map.add(DATE_SELECTION_PARAM, option.attr("value"));
		return map;
	}

	private Elements getDateOptions(String licence) throws RankingException {
		try {
			return Jsoup.parse(getPageContent(getParams(licence))).select(SELECT_DATE_SELECTOR);
		} catch (PoonaException e) {
			throw new RankingException("Failed to get ranking for player "+licence, e);
		}
	}
	
	
	private MultiValueMap<String, String> getParams(String licence) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.putAll(FORM_PARAMS);
		map.add(LICENCE_PARAM, licence);
		return map;
	}


}
