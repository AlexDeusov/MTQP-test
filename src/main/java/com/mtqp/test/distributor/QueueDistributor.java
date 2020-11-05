package com.mtqp.test.distributor;

import com.mtqp.test.dto.BidDto;
import com.mtqp.test.thread.QueuingThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QueueDistributor {

	private static final Logger LOGGER = LoggerFactory.getLogger(QueueDistributor.class);
	private static final Map<String, Set<BidDto>> BID_MAP = new HashMap<>();

	private final QueuingThread queuingThread;

	@Autowired
	public QueueDistributor(QueuingThread queuingThread) {

		this.queuingThread = queuingThread;
	}

	public void compose(List<BidDto> bids) {

		if (queuingThread.containsBids()) {
			try {
				synchronized (queuingThread) {
					queuingThread.wait();
					transferBids(bids);
					queuingThread.notify();
				}
			} catch (InterruptedException e) {
				LOGGER.error(e.getMessage());
			}
		} else {
			transferBids(bids);
			queuingThread.run();
		}
	}

	private void transferBids(List<BidDto> bids) {

		bids.forEach(bid -> {
			String type = bid.getType();

			if (!BID_MAP.containsKey(type)) {
				BID_MAP.put(type, new HashSet<>());
			}

			if (BID_MAP.get(type).add(bid)) {
				queuingThread.addBid(bid, type);
			}
		});
	}
}
