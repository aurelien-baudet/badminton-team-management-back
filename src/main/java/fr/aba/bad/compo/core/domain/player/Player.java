package fr.aba.bad.compo.core.domain.player;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import fr.aba.bad.compo.core.domain.player.info.BadInformation;

@JsonTypeInfo(use=JsonTypeInfo.Id.MINIMAL_CLASS, include=As.PROPERTY, property="@type")
public abstract class Player {
	private Long id;
	
	private BadInformation badInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BadInformation getBadInfo() {
		return badInfo;
	}

	public void setBadInfo(BadInformation badInfo) {
		this.badInfo = badInfo;
	}
	
	public Player id(Long id) {
		setId(id);
		return this;
	}
	
	public Player badInfo(BadInformation badInfo) {
		setBadInfo(badInfo);
		return this;
	}

	@Override
	public String toString() {
		return badInfo.getLicence();
	}
}
