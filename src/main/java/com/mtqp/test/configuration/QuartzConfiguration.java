package com.mtqp.test.configuration;

import com.mtqp.test.job.BidSyncJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

	@Autowired
	private ApplicationProperties applicationProperties;

	@Bean
	public JobDetail bidSyncJobDetail() {

		return JobBuilder.newJob(BidSyncJob.class).storeDurably().build();
	}

	@Bean
	public Trigger bidSyncTrigger() {

		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.repeatMinutelyForever(applicationProperties.getExecutionInterval());
		return TriggerBuilder.newTrigger().forJob(bidSyncJobDetail()).withSchedule(scheduleBuilder).build();
	}
}
