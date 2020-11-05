package com.mtqp.test.distributor;

import com.mtqp.test.dto.BidDto;
import com.mtqp.test.thread.QueuingThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QueueDistributor {

	private static final Map<String, Set<BidDto>> BID_MAP = new HashMap<>();

	private final QueuingThread queuingThread;

	@Autowired
	public QueueDistributor(QueuingThread queuingThread) {

		this.queuingThread = queuingThread;
	}

	public void compose(List<BidDto> bids) {

		transferBids(bids);

		if (!queuingThread.isAlive()) {
			queuingThread.start();
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
