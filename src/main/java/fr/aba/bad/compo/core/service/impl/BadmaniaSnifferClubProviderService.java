package fr.aba.bad.compo.core.service.impl;


import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import fr.aba.bad.compo.core.domain.player.Player;
import fr.aba.bad.compo.core.domain.team.Club;
import fr.aba.bad.compo.core.exception.club.provider.ClubProviderException;
import fr.aba.bad.compo.core.exception.service.impl.BadmaniaException;
import fr.aba.bad.compo.core.service.ClubProviderService;

//@Service
public class BadmaniaSnifferClubProviderService implements ClubProviderService {
	private static final Logger LOG = LoggerFactory.getLogger(BadmaniaSnifferClubProviderService.class);

	private static final String BADMANIA_URL = "https://badmania.fr/modules/classement/req_ajax/recherche_joueur.php";
	private static final String CLUB_NAME_CSS_SELECTOR = "tbody td:nth-child(7) span:last-child";


	@Cacheable(cacheNames="clubs", key="#player.badInfo.licence")
	@Override
	public Club getClub(Player player) throws ClubProviderException {
		return getClub(player.getBadInfo().getLicence());
	}

	@Cacheable(cacheNames="clubs", key="#licence")
	@Override
	public Club getClub(String licence) throws ClubProviderException {
		try {
			Document html = Jsoup.parse(getPageContent(BADMANIA_URL, licence));
			Elements clubCell = html.select(CLUB_NAME_CSS_SELECTOR);
			String club = clubCell.text();
			String[] parts = club.split(" - ");
			String clubName = parts[1];
			String clubAcronym = parts[0];
			// TODO: ville
			return new Club(clubName, clubAcronym, null);
		} catch(BadmaniaException e) {
			LOG.error("Failed to get club from player licence on Badmania", e);
			throw new ClubProviderException("Failed to get club from player licence "+licence, e);
		}
	}

	private String getPageContent(String url, String licence) throws BadmaniaException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("licence", licence);
		ResponseEntity<String> response = getRestTemplate().postForEntity(url, new HttpEntity<>(params, headers), String.class);
		if(response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		} else {
			throw new BadmaniaException("Failed to load Badmania search result for licence=" + licence);
		}
	}
	
	private RestTemplate getRestTemplate() {
		try {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
		        .loadTrustMaterial(null, acceptingTrustStrategy)
		        .build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom()
		        .setSSLSocketFactory(csf)
		        .build();

		HttpComponentsClientHttpRequestFactory requestFactory =
		        new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);

		    return new RestTemplate(requestFactory);
		} catch(KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
			throw new IllegalStateException(e);
		}
	}
	

}
