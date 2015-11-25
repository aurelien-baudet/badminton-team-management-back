package fr.aba.bad.compo.core.domain.notification;

import java.util.function.Function;
import java.util.function.Supplier;

import fr.aba.bad.compo.core.domain.Contact;
import fr.sii.ogham.core.message.Message;
import fr.sii.ogham.email.message.Email;
import fr.sii.ogham.sms.message.Sms;

public enum Channel {
	MAIL(Email::new, c -> c.getEmail()),
	SMS(Sms::new, c -> c.getPhoneNumber());
	
	private final Supplier<Message> supplier;
	
	private final Function<Contact, String> contactAccessor;

	private Channel(Supplier<Message> supplier, Function<Contact, String> contactAccessor) {
		this.supplier = supplier;
		this.contactAccessor = contactAccessor;
	}

	public Message getMessage() {
		return supplier.get();
	}

	public String getContactInfo(Contact contact) {
		return contactAccessor.apply(contact);
	}
	
	
}
