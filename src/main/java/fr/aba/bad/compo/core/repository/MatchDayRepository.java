package fr.aba.bad.compo.core.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.aba.bad.compo.core.domain.match.MatchDay;

@RepositoryRestResource
public interface MatchDayRepository extends JpaRepository<MatchDay, Long> {
	List<MatchDay> findByCalendarEndBefore(LocalDate date);
}