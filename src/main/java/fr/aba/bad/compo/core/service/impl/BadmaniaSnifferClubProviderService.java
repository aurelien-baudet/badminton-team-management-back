package fr.aba.bad.compo.core.service.impl;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;

import fr.aba.bad.compo.config.WebDriverConfig.WebDriverFactory;
import fr.aba.bad.compo.core.domain.player.Player;
import fr.aba.bad.compo.core.domain.team.Club;
import fr.aba.bad.compo.core.exception.club.provider.ClubProviderException;
import fr.aba.bad.compo.core.service.ClubProviderService;

@Service
public class BadmaniaSnifferClubProviderService implements ClubProviderService {

	private static final String BADMANIA_URL = "https://badmania.fr/classement.html#menu";
	private static final String TAB_RESULT_ID = "tab_resultJoueur";
	private static final String CLUB_NAME_CSS_SELECTOR = "#"+TAB_RESULT_ID+" tbody td:nth-child(7)";
	private static final String LICENCE_INPUT_ID = "licence";

	private final WebDriverFactory factory;
	
	@Autowired
	public BadmaniaSnifferClubProviderService(WebDriverFactory factory) {
		super();
		this.factory = factory;
	}

	@Cacheable(cacheNames="clubs", key="#player.badInfo.licence")
	@Override
	public synchronized Club getClub(Player player) throws ClubProviderException {
		return getClub(player.getBadInfo().getLicence());
	}

	@Cacheable(cacheNames="clubs", key="#licence")
	@Override
	public synchronized Club getClub(String licence) throws ClubProviderException {
		WebDriver driver = factory.create();
		try {
			driver.get(BADMANIA_URL);
			driver.findElement(By.id(LICENCE_INPUT_ID)).sendKeys(licence);
			WebDriverWait wait = new WebDriverWait(driver, 10000L);
			wait.until(isResultLoaded());
			WebElement clubCell = driver.findElement(By.cssSelector(CLUB_NAME_CSS_SELECTOR));
			String club = clubCell/*.findElement(By.cssSelector("a span:last-child"))*/.getText();
			String[] parts = club.split(" - ");
			String clubName = parts[1];
			String clubAcronym = parts[0];
			// TODO: ville
			return new Club(clubName, clubAcronym, null);
		} finally {
			driver.quit();
		}
	}

	private Predicate<WebDriver> isResultLoaded() {
		return d -> !d.findElements(By.id(TAB_RESULT_ID)).isEmpty();
	}

}
