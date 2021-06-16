package com.javabrains.fullstack.IPLDashboard.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Team {

	@Id
	@GeneratedValue
	private long id;
	private String teamName;
	private long totalMatchesPlayed;
	private long totalWon;

	@Transient
	private List<MatchEntityData> list;

	public Team(String teamName, long totalMatchesPlayed) {
		this.teamName = teamName;
		this.totalMatchesPlayed = totalMatchesPlayed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public long getTotalMatchesPlayed() {
		return totalMatchesPlayed;
	}

	public void setTotalMatchesPlayed(long totalMatchesPlayed) {
		this.totalMatchesPlayed = totalMatchesPlayed;
	}

	public long getTotalWon() {
		return totalWon;
	}

	public void setTotalWon(long totalWon) {
		this.totalWon = totalWon;
	}

	public List<MatchEntityData> getList() {
		return list;
	}

	public void setList(List<MatchEntityData> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + ", totalMatchesPlayed=" + totalMatchesPlayed
				+ ", totalWon=" + totalWon + "]";
	}

	public Team () {
		
	}
}
