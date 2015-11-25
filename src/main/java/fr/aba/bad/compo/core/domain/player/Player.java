package fr.aba.bad.compo.core.domain.player;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import fr.aba.bad.compo.core.domain.player.info.BadInformation;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use=JsonTypeInfo.Id.MINIMAL_CLASS, include=As.PROPERTY, property="@type")
public abstract class Player {
	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
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
