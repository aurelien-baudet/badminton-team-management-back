package fr.aba.bad.compo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.aba.bad.compo.core.domain.notification.Notification;

@RepositoryRestResource
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
