package fr.aba.bad.compo.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {
	@Bean
	public WebDriverFactory driver() {
		return HtmlUnitDriver::new;
	}
	
	@FunctionalInterface
	public static interface WebDriverFactory {
		public WebDriver create();
	}
}
