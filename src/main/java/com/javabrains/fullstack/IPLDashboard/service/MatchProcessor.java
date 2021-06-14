package com.javabrains.fullstack.IPLDashboard.service;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import com.javabrains.fullstack.IPLDashboard.pojo.MatchDataFromFileVO;
import com.javabrains.fullstack.IPLDashboard.pojo.MatchEntityData;

public class MatchProcessor implements ItemProcessor<MatchDataFromFileVO, MatchEntityData> {

	@Override
	public MatchEntityData process(MatchDataFromFileVO item) throws Exception {

		MatchEntityData data = new MatchEntityData();

		data.setId(Long.parseLong(item.getId()));
		data.setCity(item.getCity());
		data.setDate(LocalDate.parse(item.getDate()));
		data.setPlayerOfMatch(item.getPlayer_of_match());
		data.setVenue(item.getVenue());
		data.setTossDecision(item.getToss_decision());
		data.setTossWinner(item.getToss_winner());
		data.setWinner(item.getWinner());
		data.setResult(item.getResult());
		data.setEliminator(item.getEliminator());
		data.setResultMargin(item.getResult_margin());
		data.setMethod(item.getMethod());
		data.setUmpire1(item.getUmpire1());
		data.setUmpire2(item.getUmpire2());

		// team batting first will always be Team 1

		// team who won the toss and decided to bat first or team who lost the toss and
		// was made to bat - Team 1
		if (item.getToss_decision().equals("bat")) {
			data.setTeam1(item.getToss_winner());
			if (item.getTeam1().equals(item.getToss_winner())) {
				data.setTeam2(item.getTeam2());
			} else {
				data.setTeam2(item.getTeam1());
			}

		} else {
			data.setTeam2(item.getToss_winner());
			if (item.getTeam1().equals(item.getToss_winner())) {
				data.setTeam1(item.getTeam2());
			} else {
				data.setTeam1(item.getTeam1());
			}
		}
		return data;

	}
}
