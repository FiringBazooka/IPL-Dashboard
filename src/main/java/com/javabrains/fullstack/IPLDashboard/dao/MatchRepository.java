package com.javabrains.fullstack.IPLDashboard.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javabrains.fullstack.IPLDashboard.pojo.MatchEntityData;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntityData, Long> {

	List<MatchEntityData> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

	List<MatchEntityData> findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(String team1,
			LocalDate startDate1, LocalDate endDate1, String team2, LocalDate startDate2, LocalDate endDate2);

	default List<MatchEntityData> getLatestMatches(String teamName, int count) {
		return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
	}
}
