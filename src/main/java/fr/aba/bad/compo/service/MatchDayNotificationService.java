package fr.aba.bad.compo.service;

import fr.aba.bad.compo.exception.NotificationException;

public interface MatchDayNotificationService {
	public void sendNotification(Long matchdayId, Long notificationId) throws NotificationException;
}
