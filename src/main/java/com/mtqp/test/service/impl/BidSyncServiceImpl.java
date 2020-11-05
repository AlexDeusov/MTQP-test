package com.mtqp.test.service.impl;

import com.mtqp.test.job.BidSyncJob;
import com.mtqp.test.distributor.QueueDistributor;
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
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	private final ConverterService converterService;
	private final QueueDistributor queueDistributor;

	@Autowired
	public BidSyncServiceImpl(ConverterService converterService, QueueDistributor queueDistributor) {

		this.converterService = converterService;
		this.queueDistributor = queueDistributor;
	}

	@Override
	public void synchronize() {

		var bids = converterService.readJson();
		LOGGER.info("Bids have been synchronized at " + dateFormat.format(new Date()));
		queueDistributor.compose(bids);
	}
}
