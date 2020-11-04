package com.mtqp.test.task;

import com.mtqp.test.service.ConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JsonReadingTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonReadingTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	private final ConverterService converterService;

	@Autowired
	public JsonReadingTask(ConverterService converterService) {

		this.converterService = converterService;
	}

	@Scheduled(fixedRate = 60000)
	public void performTask() {

		var bids = converterService.readJson();
		LOGGER.info("Json has read at " + dateFormat.format(new Date()) + ", " + bids.size() + " bids found");
	}
}
