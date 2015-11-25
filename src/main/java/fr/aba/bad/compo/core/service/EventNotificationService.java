package fr.aba.bad.compo.core.service;

import java.util.List;

import fr.aba.bad.compo.core.domain.notification.Notification;

public interface EventNotificationService {
	public List<Notification> getEventsToTrigger();
	
	public void trigger(List<Notification> events);
	
	public void trigger(Notification event);
}
