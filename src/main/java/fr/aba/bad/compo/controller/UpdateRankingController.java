package fr.aba.bad.compo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.aba.bad.compo.exception.ranking.RankingException;
import fr.aba.bad.compo.service.RankingUpdaterService;

@Controller
@RequestMapping("rankings/players")
public class UpdateRankingController {
	@Autowired
	RankingUpdaterService rankingUpdaterService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void update() throws RankingException {
		rankingUpdaterService.updateAllRankings();
	}
}
