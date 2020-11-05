package com.mtqp.test.utils;

import com.mtqp.test.dto.BidDto;

import java.util.Base64;

public final class BidUtils {

	public static String createLoggingInfo(BidDto dto) {

		return "Bid id: " + dto.getId() + ", timestamp: " + dto.getTimestamp() + ", queue \"name\": " + dto.getType() + ", payload: " + decodePayload(dto.getPayload()) + ".";
	}

	private static String decodePayload(String payload) {

		return new String(Base64.getDecoder().decode(payload));
	}
}
