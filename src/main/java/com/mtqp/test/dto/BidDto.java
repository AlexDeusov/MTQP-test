package com.mtqp.test.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class BidDto {

	private final Long id;
	private final Long timestamp;
	private final String type;
	private final String payload;

	@JsonCreator
	public BidDto(@JsonProperty("id") Long id, @JsonProperty("ts") Long timestamp, @JsonProperty("ty") String type, @JsonProperty("pl") String payload) {

		this.id = id;
		this.timestamp = timestamp;
		this.type = type;
		this.payload = payload;
	}

	public Long getId() {

		return id;
	}

	public Long getTimestamp() {

		return timestamp;
	}

	public String getType() {

		return type;
	}

	public String getPayload() {

		return payload;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof BidDto))
			return false;
		BidDto bidDto = (BidDto) o;
		return id.equals(bidDto.id) && timestamp.equals(bidDto.timestamp) && type.equals(bidDto.type) && payload.equals(bidDto.payload);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, timestamp, type, payload);
	}
}
