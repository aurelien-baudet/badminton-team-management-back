package fr.aba.bad.compo.service.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.aba.bad.compo.exception.NotificationException;
import fr.aba.bad.compo.service.MatchDayNotificationService;

public class SendMessageJob implements Job {
	private static final Logger LOG = LoggerFactory.getLogger(SendMessageJob.class);
	
	@Autowired
	MatchDayNotificationService matchDayNotificationService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Long notificationId = context.getMergedJobDataMap().getLong("notificationId");
		Long matchdayId = context.getMergedJobDataMap().getLong("matchdayId");
		try {
			matchDayNotificationService.sendNotification(matchdayId, notificationId);
		} catch (NotificationException e) {
			LOG.error("Failed to send message for matchday "+matchdayId+" ("+notificationId+")", e);
			throw new JobExecutionException("Failed to send message for matchday "+matchdayId+" ("+notificationId+")", e);
		}
	}
	
}
