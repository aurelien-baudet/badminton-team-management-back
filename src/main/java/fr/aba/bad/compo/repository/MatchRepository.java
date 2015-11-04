package fr.aba.bad.compo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.aba.bad.compo.domain.match.Match;

@RepositoryRestResource
public interface MatchRepository extends JpaRepository<Match, Long> {

}
