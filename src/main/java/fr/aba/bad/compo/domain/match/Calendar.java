package fr.aba.bad.compo.domain.match;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

@Embeddable
public class Calendar {
	@Column
	private LocalDate start;
	
	@Column
	private LocalDate end;
	
	@ElementCollection
	private List<LocalDateTime> possibleDates;
	
	@Column
	private LocalDateTime playDate;
	
	@Column
	private LocalDate rankDate;

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public LocalDateTime getPlayDate() {
		return playDate;
	}

	public void setPlayDate(LocalDateTime playDate) {
		this.playDate = playDate;
	}

	public LocalDate getRankDate() {
		return rankDate;
	}

	public void setRankDate(LocalDate rankDate) {
		this.rankDate = rankDate;
	}

	public List<LocalDateTime> getPossibleDates() {
		return possibleDates;
	}

	public void setPossibleDates(List<LocalDateTime> possibleDates) {
		this.possibleDates = possibleDates;
	}
	
	
	
}
