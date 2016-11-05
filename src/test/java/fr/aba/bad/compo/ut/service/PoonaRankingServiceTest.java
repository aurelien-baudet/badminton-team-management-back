package fr.aba.bad.compo.ut.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fr.aba.bad.compo.core.domain.player.InternalPlayer;
import fr.aba.bad.compo.core.domain.player.info.BadInformation;
import fr.aba.bad.compo.core.domain.rank.Rank;
import fr.aba.bad.compo.core.domain.rank.Ranking;
import fr.aba.bad.compo.core.exception.ranking.RankingException;
import fr.aba.bad.compo.core.service.impl.PoonaSnifferService;

public class PoonaRankingServiceTest {
	PoonaSnifferService rankingService;
	
	@Before
	public void setup() {
		rankingService = new PoonaSnifferService();
	}
	
	@Test
	public void playerRanking() throws RankingException {
		Ranking ranking = rankingService.getRanking(new InternalPlayer().badInfo(new BadInformation().licence("6753956")), LocalDate.of(2016, 11, 3));
		assertEquals("Should be D9/P11/D9", Rank.D9, ranking.getSingles());
		assertEquals("Should be D9/P11/D9", Rank.P11, ranking.getDoubles());
		assertEquals("Should be D9/P11/D9", Rank.D9, ranking.getMixedDoubles());
		assertEquals("Date should be 2016/11/03", LocalDate.of(2016, 11, 3), ranking.getDate());
	}
	
	@Test
	public void playerRanking2() throws RankingException {
		Ranking ranking = rankingService.getRanking(new InternalPlayer().badInfo(new BadInformation().licence("06459913")), LocalDate.of(2016, 11, 3));
		assertEquals("Should be P10/D9/D8", Rank.P10, ranking.getSingles());
		assertEquals("Should be P10/D9/D8", Rank.D9, ranking.getDoubles());
		assertEquals("Should be P10/D9/D8", Rank.D8, ranking.getMixedDoubles());
		assertEquals("Date should be 2016/11/03", LocalDate.of(2016, 11, 3), ranking.getDate());
	}
	
	@Test
	@Ignore
	public void playerRankings() throws RankingException {
		List<Ranking> rankings = rankingService.getRankings(new InternalPlayer().badInfo(new BadInformation().licence("6753956")));
		System.out.println(rankings);
	}
}
