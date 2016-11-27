package fr.aba.bad.compo.web.controller;

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

import fr.aba.bad.compo.core.domain.rank.Rank;
import fr.aba.bad.compo.core.domain.rank.RankGroup;
import fr.aba.bad.compo.core.domain.rank.Ranking;
import fr.aba.bad.compo.core.exception.ranking.RankingException;
import fr.aba.bad.compo.core.service.RankingOrderService;
import fr.aba.bad.compo.core.service.RankingProviderService;

@RestController
@RequestMapping("rankings")
public class RankingController {
	@Autowired
	RankingProviderService rankingProviderService;
	
	@Autowired
	RankingOrderService rankingOrderService;
	
	@RequestMapping(value="{licence:[0-9]+}/{date}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Ranking getRanking(@PathVariable String licence, @PathVariable @DateTimeFormat(iso=ISO.DATE) LocalDate date) throws RankingException {
		return rankingProviderService.getRanking(licence, date);
	}
	
	@RequestMapping(value="{greater:[NRDP][0-9C]+}/{lower:[NRDP][0-9C]+}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public boolean isGreater(@PathVariable Rank greater, @PathVariable Rank lower) throws RankingException {
		return rankingOrderService.isGreater(greater, lower);
	}
	
	@RequestMapping(value="{greater1:[NRDP][0-9C]+}-{greater2:[NRDP][0-9C]+}/{lower1:[NRDP][0-9C]+}-{lower2:[NRDP][0-9C]+}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public boolean isGreater(@PathVariable Rank greater1, @PathVariable Rank greater2, @PathVariable Rank lower1, @PathVariable Rank lower2) throws RankingException {
		return rankingOrderService.isGreater(new RankGroup(greater1, greater2), new RankGroup(lower1, lower2));
	}
}
