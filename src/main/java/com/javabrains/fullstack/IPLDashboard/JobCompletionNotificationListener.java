package com.javabrains.fullstack.IPLDashboard;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javabrains.fullstack.IPLDashboard.pojo.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final EntityManager em;

	@Autowired
	public JobCompletionNotificationListener(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			Map<String, Team> teamData = new HashMap<>();

			em.createQuery("select team1, count(*) from MATCH group by team1", Object[].class).getResultList().stream()
					.map(o -> new Team((String) o[0], (long) o[1]))
					.forEach(team -> teamData.put(team.getTeamName(), team));

			em.createQuery("select team2, count(*) from MATCH group by team2", Object[].class).getResultList().stream()
					.forEach(o -> {
						Team team = teamData.get(o[0]);
						team.setTotalMatchesPlayed(team.getTotalMatchesPlayed() + (long) o[1]);
					});

			em.createQuery("select winner,count(*) from MATCH group by winner", Object[].class).getResultList().stream()
					.forEach(o -> {
						Team team = teamData.get(o[0]);
						if (team != null)
							team.setTotalWon((long) o[1]);
					});

			teamData.values().forEach(team -> em.persist(team));
			teamData.values().forEach(team -> System.out.println(team));

		}

	}
}