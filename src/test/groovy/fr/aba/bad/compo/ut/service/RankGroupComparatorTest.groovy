package fr.aba.bad.compo

import spock.lang.Specification
import fr.aba.bad.compo.domain.rank.Rank
import fr.aba.bad.compo.domain.rank.RankGroup
import fr.aba.bad.compo.service.impl.RankGroupComparator

class RankGroupComparatorTest extends Specification {
	def "rank comparator"(RankGroup rankGroup1, RankGroup rankGroup2, int result) {
		/**
		Suite à quelques questions de votre part concernant les moyennes des paires de mixtes et surtout comment gérer les classements P et NC, voici un éclaircissement sur le calcul de la moyenne d'une paire.
		
		L'ordre décroissant des classements tel que définis par la FFBaD : N1 N2 N3 R4 R5 R6 D7 D8 D9 P1 P2 P3 NC
		Donc une paire R6/NC vaut en moyenne D9/P1 soit D9,5
		Un paire D7/NC vaut P1
		
		Prenons un exemple :
		Une paire P1/P3 vaut P2
		Une paire D9/NC vaut P2
		Donc vous pouvez les mettre indifféremment en mixte 1 ou mixte 2
		*/
		expect:
			new RankGroupComparator().compare(rankGroup1, rankGroup2) == result

		where:
			rankGroup1 								| rankGroup2 								| result
			new RankGroup(Rank.NC) 					| new RankGroup(Rank.NC) 					| 0
			new RankGroup(Rank.P3) 					| new RankGroup(Rank.NC) 					| 1
			new RankGroup(Rank.D9, Rank.D9)			| new RankGroup(Rank.D8, Rank.P1)			| 0
			new RankGroup(Rank.D8, Rank.NC)			| new RankGroup(Rank.D9, Rank.P1)			| -1
			new RankGroup(Rank.R6, Rank.NC)			| new RankGroup(Rank.D9, Rank.P1)			| 0
			new RankGroup(Rank.D7, Rank.NC)			| new RankGroup(Rank.P1, Rank.P1)			| 0
			new RankGroup(Rank.P3, Rank.P1)			| new RankGroup(Rank.P2, Rank.P2)			| 0
			new RankGroup(Rank.D9, Rank.NC)			| new RankGroup(Rank.P2, Rank.P2)			| 0
	}
}
