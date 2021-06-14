package com.javabrains.fullstack.IPLDashboard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javabrains.fullstack.IPLDashboard.pojo.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	Team findByTeamName(String teamName);

}
