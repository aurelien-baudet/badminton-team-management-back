package fr.aba.bad.compo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.aba.bad.compo.domain.team.Division;

@RepositoryRestResource
public interface DivisionRepository extends JpaRepository<Division, Long> {

}
