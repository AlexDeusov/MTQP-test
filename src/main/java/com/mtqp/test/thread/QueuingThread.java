package com.mtqp.test.thread;

import com.mtqp.test.dto.BidDto;
import com.mtqp.test.utils.BidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class QueuingThread extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(QueuingThread.class);
	private static final Map<String, Queue<BidDto>> QUEUE_MAP = Collections.synchronizedMap(new HashMap<>());

	@Override
	public void run() {

		while (!QUEUE_MAP.isEmpty()) {
			QUEUE_MAP.values().forEach(this::queuingIteration);
		}
	}

	public void addBid(BidDto dto, String type) {

		if (!QUEUE_MAP.containsKey(type)) {
			QUEUE_MAP.put(type, new LinkedBlockingQueue<>());
		}
		QUEUE_MAP.get(type).add(dto);
		LOGGER.info("Bid with id " + dto.getId() + " has added to queue " + type);
	}

	private void queuingIteration(Queue<BidDto> queue) {

		if (!queue.isEmpty()) {
			var completableFuture = CompletableFuture.supplyAsync(() -> {
				BidDto head = queue.poll();
				return BidUtils.createLoggingInfo(Objects.requireNonNull(head));
			});

			var future = completableFuture.thenAcceptAsync(LOGGER::info);

			try {
				future.get();
			} catch (InterruptedException | ExecutionException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}
}
