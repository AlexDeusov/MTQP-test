package com.mtqp.test.pool;

import com.mtqp.test.dto.BidDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class QueuePool {

	private static final Logger LOGGER = LoggerFactory.getLogger(QueuePool.class);
	private static final Map<String, Set<BidDto>> BID_MAP = new HashMap<>();
	private static final Map<String, Queue<BidDto>> WORK_MAP = new HashMap<>();

	public void compose(List<BidDto> bids) {

		bids.forEach(bid -> {
			String type = bid.getType();

			if (!BID_MAP.containsKey(type)) {
				BID_MAP.put(type, new HashSet<>());
			}

			if (BID_MAP.get(type).add(bid)) {
				if (!WORK_MAP.containsKey(type)) {
					WORK_MAP.put(type, new LinkedList<>());
				}
				WORK_MAP.get(type).add(bid);
			}
		});

		LOGGER.info("Set's number: " + BID_MAP.size() + "; queue's number: " + WORK_MAP.size());
		BID_MAP.forEach((key, value) -> {
			LOGGER.info(key + " type value amount: " + value.size());
		});

		WORK_MAP.forEach((key, value) -> LOGGER.info(key + " type:" + value));
	}
}
