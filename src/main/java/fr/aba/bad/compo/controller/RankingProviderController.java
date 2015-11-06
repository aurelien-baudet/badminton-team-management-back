package fr.aba.bad.compo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.aba.bad.compo.domain.rank.Ranking;
import fr.aba.bad.compo.exception.ranking.RankingException;
import fr.aba.bad.compo.service.RankingProviderService;

@RestController
@RequestMapping("rankings")
public class RankingProviderController {
	@Autowired
	RankingProviderService rankingProviderService;
	
	@RequestMapping(value="{licence}/{date}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Ranking getRanking(@PathVariable String licence, @PathVariable @DateTimeFormat(iso=ISO.DATE) LocalDate date) throws RankingException {
		return rankingProviderService.getRanking(licence, date);
	}
}
