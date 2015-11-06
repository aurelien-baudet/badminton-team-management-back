package fr.aba.bad.compo.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.aba.bad.compo.domain.match.MatchDay;
import fr.aba.bad.compo.domain.player.Player;
import fr.aba.bad.compo.domain.rank.Ranking;
import fr.aba.bad.compo.exception.ranking.RankingDateException;
import fr.aba.bad.compo.exception.ranking.RankingException;
import fr.aba.bad.compo.repository.MatchDayRepository;
import fr.aba.bad.compo.repository.PlayerRepository;
import fr.aba.bad.compo.repository.RankingRepository;
import fr.aba.bad.compo.service.RankingProviderService;
import fr.aba.bad.compo.service.RankingUpdaterService;

@Service
@Transactional
public class CronRankingUpdaterService implements RankingUpdaterService {
	private static final Logger LOG = LoggerFactory.getLogger(CronRankingUpdaterService.class);
	
	@Autowired
	MatchDayRepository matchDayRepository;
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	RankingRepository rankingRepository;
	
	@Autowired
	RankingProviderService rankingProviderService;
	
	@Override
	@Scheduled(cron="0 0 4 * * *")
	public void updateAllRankings() throws RankingException {
		for(Player player : playerRepository.findAll()) {
			updatePlayer(player);
		}
	}
	
	private void updatePlayer(Player player) throws RankingException {
		for(MatchDay matchDay : matchDayRepository.findAll()) {
			if(matchDay.getCalendar().getRankDate().isBefore(LocalDate.now())) {
				try {
					updatePlayer(player, rankingProviderService.getRanking(player, matchDay.getCalendar().getRankDate()));
				} catch(RankingDateException e) {
					LOG.info(e.getMessage(), e);
				}
			}
		}
	}

	private void updatePlayer(Player player, Ranking ranking) {
		List<Ranking> rankings = player.getBadInfo().getRankings();
		if(!rankings.stream().anyMatch(r -> r.getDate().equals(ranking.getDate()))) {
			rankings.add(rankingRepository.save(ranking));
		}
		playerRepository.save(player);
	}
}
