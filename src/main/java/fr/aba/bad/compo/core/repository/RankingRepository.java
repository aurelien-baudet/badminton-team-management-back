package fr.aba.bad.compo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.aba.bad.compo.core.domain.rank.Ranking;

@RepositoryRestResource
public interface RankingRepository extends JpaRepository<Ranking, Long> {

}
