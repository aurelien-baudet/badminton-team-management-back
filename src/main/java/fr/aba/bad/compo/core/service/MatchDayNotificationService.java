package fr.aba.bad.compo.core.service;

import fr.aba.bad.compo.core.exception.NotificationException;

public interface MatchDayNotificationService {
	public void sendNotification(Long matchdayId, Long notificationId) throws NotificationException;
}
