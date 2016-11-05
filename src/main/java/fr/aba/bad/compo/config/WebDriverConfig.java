package fr.aba.bad.compo.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {
	@Bean
	public WebDriverFactory firefox() {
		return () -> new FirefoxDriver();
	}
	
	@FunctionalInterface
	public static interface WebDriverFactory {
		public WebDriver create();
	}
}
