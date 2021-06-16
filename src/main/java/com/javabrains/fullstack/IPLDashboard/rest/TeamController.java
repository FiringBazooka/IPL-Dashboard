package com.javabrains.fullstack.IPLDashboard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.javabrains.fullstack.IPLDashboard.dao.MatchRepository;
import com.javabrains.fullstack.IPLDashboard.dao.TeamRepository;
import com.javabrains.fullstack.IPLDashboard.pojo.Team;

@RestController
@CrossOrigin
public class TeamController {

	@Autowired
	private TeamRepository teamDao;

	@Autowired
	private MatchRepository matchDao;

	@GetMapping("/team/{teamName}")
	public Team getTeamDetails(@PathVariable String teamName) {

		Team team = teamDao.findByTeamName(teamName);
		team.setList(matchDao.getLatestMatches(teamName, 4));

		return team;
	}
}
