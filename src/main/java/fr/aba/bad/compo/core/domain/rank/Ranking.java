package fr.aba.bad.compo.core.domain.rank;

import java.time.LocalDate;

public class Ranking {
	private Long id;
	
	private LocalDate date;
	
	private Rank singles;
	
	private Rank doubles;
	
	private Rank mixedDoubles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Rank getSingles() {
		return singles;
	}

	public void setSingles(Rank singles) {
		this.singles = singles;
	}

	public Rank getDoubles() {
		return doubles;
	}

	public void setDoubles(Rank doubles) {
		this.doubles = doubles;
	}

	public Rank getMixedDoubles() {
		return mixedDoubles;
	}

	public void setMixedDoubles(Rank mixedDoubles) {
		this.mixedDoubles = mixedDoubles;
	}
	
	
	public Ranking id(Long id) {
		this.id = id;
		return this;
	}
	
	public Ranking date(LocalDate date) {
		this.date = date;
		return this;
	}
	
	public Ranking singles(Rank singles) {
		this.singles = singles;
		return this;
	}
	
	public Ranking doubles(Rank doubles) {
		this.doubles = doubles;
		return this;
	}
	
	public Ranking mixedDoubles(Rank mixedDoubles) {
		this.mixedDoubles = mixedDoubles;
		return this;
	}

	@Override
	public String toString() {
		return singles+"/"+doubles+"/"+mixedDoubles+" (id:" + id + ", date:" + date + ")";
	}

	
	
}
