package fr.aba.bad.compo.service.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aba.bad.compo.domain.Contact;
import fr.aba.bad.compo.domain.match.MatchDay;
import fr.aba.bad.compo.domain.notification.Notification;
import fr.aba.bad.compo.exception.NotificationException;
import fr.aba.bad.compo.repository.MatchDayRepository;
import fr.aba.bad.compo.repository.NotificationRepository;
import fr.aba.bad.compo.service.MatchDayNotificationService;
import fr.sii.ogham.core.exception.MessagingException;
import fr.sii.ogham.core.message.Message;
import fr.sii.ogham.core.message.capability.HasRecipients;
import fr.sii.ogham.core.message.capability.HasToFluent;
import fr.sii.ogham.core.message.content.TemplateContent;
import fr.sii.ogham.core.service.MessagingService;

@Transactional
@Service
public class OghamMatchDayNotificationService implements MatchDayNotificationService {
	private static final Logger LOG = LoggerFactory.getLogger(OghamMatchDayNotificationService.class);
	
	@Autowired
	MessagingService messagingService;

	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	MatchDayRepository matchDayRepository;
	
	public void sendNotification(Long matchdayId, Long notificationId) throws NotificationException {
		try {
			MatchDay matchday = matchDayRepository.findOne(matchdayId);
			Notification notif = notificationRepository.findOne(notificationId);
			Message message = notif.getChannel().getMessage();
			message.setContent(new TemplateContent(notif.getName(), matchday));
			// TODO: handle cc
			for(Contact contact : notif.getRecipients().getContacts(matchday)) {
				((HasToFluent<?>) message).to(notif.getChannel().getContactInfo(contact));
			}
			LOG.info("Sending "+notif.getName()+" "+message.getClass().getTypeName()+" to "+((HasRecipients<?>) message).getRecipients());
			messagingService.send(message);
		} catch(MessagingException e) {
			throw new NotificationException(e.getMessage(), e);
		}
	}

}
