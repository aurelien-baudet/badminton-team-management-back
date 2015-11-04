package fr.aba.bad.compo.domain.notification;

import java.time.Duration;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Notification {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@Column
	@Enumerated(EnumType.STRING)
	private DateField dateField;
	
	@Column
	private Duration triggerDelta;
	
	@Column
	private LocalTime triggerTime;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Channel channel;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Recipients recipients;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public DateField getDateField() {
		return dateField;
	}

	public void setDateField(DateField dateField) {
		this.dateField = dateField;
	}

	public Duration getTriggerDelta() {
		return triggerDelta;
	}

	public void setTriggerDelta(Duration trigger) {
		this.triggerDelta = trigger;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public LocalTime getTriggerTime() {
		return triggerTime;
	}

	public void setTriggerTime(LocalTime triggerTime) {
		this.triggerTime = triggerTime;
	}

	public Recipients getRecipients() {
		return recipients;
	}

	public void setRecipients(Recipients recipient) {
		this.recipients = recipient;
	}
}
