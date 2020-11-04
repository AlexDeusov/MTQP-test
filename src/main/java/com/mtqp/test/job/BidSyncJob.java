package com.mtqp.test.job;

import com.mtqp.test.service.BidSyncService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class BidSyncJob extends QuartzJobBean {

	private final BidSyncService syncService;

	@Autowired
	public BidSyncJob(BidSyncService syncService) {

		this.syncService = syncService;
	}

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		syncService.synchronize();
	}
}
