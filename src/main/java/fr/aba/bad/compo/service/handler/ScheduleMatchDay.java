package fr.aba.bad.compo.service.handler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import fr.aba.bad.compo.domain.match.MatchDay;
import fr.aba.bad.compo.domain.notification.DateField;
import fr.aba.bad.compo.domain.notification.Notification;
import fr.aba.bad.compo.repository.NotificationRepository;
import fr.aba.bad.compo.service.job.SendMessageJob;

@Component
@RepositoryEventHandler
public class ScheduleMatchDay {
	private static final String JOB_GROUP = "matchday";
	
	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	NotificationRepository notificationRepository;
	
	@HandleAfterSave
	public void schedule(MatchDay match) throws SchedulerException {
		for(Notification event : notificationRepository.findAll()) {
			schedule(match, getDate(match, event.getDateField()), event);
		}
	}
	
	private Temporal getDate(MatchDay matchday, DateField field) {
		return field.getDate(matchday);
	}
	
	private void schedule(MatchDay match, Temporal date, Notification notification) throws SchedulerException {
		String type = notification.getName();
		JobDetail detail = JobBuilder.newJob(SendMessageJob.class).
					withIdentity(type+match.getId().toString(), JOB_GROUP).
					requestRecovery().
					usingJobData("notificationId", notification.getId().toString()).
					usingJobData("matchdayId", match.getId().toString()).
					storeDurably().
					build();
		Trigger trigger = TriggerBuilder.newTrigger().
					withIdentity(type+match.getId().toString(), JOB_GROUP).
					startAt(computeTriggerDate(match, notification)).
					build();
		schedulerFactoryBean.getScheduler().scheduleJob(detail, trigger);		
	}

	private Date computeTriggerDate(MatchDay match, Notification event) {
		ZonedDateTime triggerDate = match.getCalendar().getStart().
									atTime(event.getTriggerTime()).
									plus(event.getTriggerDelta()).
									atZone(ZoneId.systemDefault());
		return Date.from(triggerDate.toInstant());
	}
}
