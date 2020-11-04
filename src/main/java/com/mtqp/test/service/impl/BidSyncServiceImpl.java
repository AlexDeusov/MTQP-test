package com.mtqp.test.service.impl;

import com.mtqp.test.job.BidSyncJob;
import com.mtqp.test.pool.QueuePool;
import com.mtqp.test.service.ConverterService;
import com.mtqp.test.service.BidSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BidSyncServiceImpl implements BidSyncService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BidSyncJob.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	private final ConverterService converterService;
	private final QueuePool queuePool;

	@Autowired
	public BidSyncServiceImpl(ConverterService converterService, QueuePool queuePool) {

		this.converterService = converterService;
		this.queuePool = queuePool;
	}

	@Override
	public void synchronize() {

		var bids = converterService.readJson();
		LOGGER.info("Json has read at " + dateFormat.format(new Date()));
		queuePool.compose(bids);
	}
}
