package fr.aba.bad.compo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.aba.bad.compo.core.domain.team.Team;

@RepositoryRestResource
public interface TeamRepository extends JpaRepository<Team, Long> {
}
