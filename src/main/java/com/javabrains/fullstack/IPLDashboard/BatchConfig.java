package com.javabrains.fullstack.IPLDashboard;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.javabrains.fullstack.IPLDashboard.pojo.MatchDataFromFileVO;
import com.javabrains.fullstack.IPLDashboard.pojo.MatchEntityData;
import com.javabrains.fullstack.IPLDashboard.service.MatchProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	private static final String[] FIELD_NAMES = { "id", "city", "date", "player_of_match", "venue", "neutral_venue",
			"team1", "team2", "toss_winner", "toss_decision", "winner", "result", "result_margin", "eliminator",
			"method", "umpire1", "umpire2" };

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader<MatchDataFromFileVO> reader() {

		return new FlatFileItemReaderBuilder<MatchDataFromFileVO>().name("csvReader")
				.resource(new ClassPathResource("match_data_set_2008_2020.csv")).delimited().names(FIELD_NAMES)
				.linesToSkip(1).fieldSetMapper(new BeanWrapperFieldSetMapper<MatchDataFromFileVO>() {
					{
						setTargetType(MatchDataFromFileVO.class);
					}
				}).build();
	}

	@Bean
	public MatchProcessor processor() {
		return new MatchProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<MatchEntityData> writer(DataSource dataSource) {

		return new JdbcBatchItemWriterBuilder<MatchEntityData>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO MATCH (id,	city,	date,	player_of_match,	venue,	team1,	team2,	toss_winner,	toss_decision,	winner,	result, result_margin,	eliminator,	method,	umpire1,	umpire2)"
						+ " VALUES (:id,	:city,	:date,	:playerOfMatch,	:venue,	:team1,	:team2,	:tossWinner,	:tossDecision,	:winner,	:result, :resultMargin,	:eliminator,	:method,	:umpire1,	:umpire2)")
				.dataSource(dataSource).build();

	}

	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
				.end().build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<MatchEntityData> writer) {
		return stepBuilderFactory.get("step1").<MatchDataFromFileVO, MatchEntityData>chunk(10).reader(reader())
				.processor(processor()).writer(writer).build();
	}

}
