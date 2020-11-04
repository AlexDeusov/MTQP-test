package com.mtqp.test.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Base64;

public class BidDto {

	private final Long id;
	private final Long ts;
	private final String ty;
	private final String pl;

	@JsonCreator
	public BidDto(@JsonProperty("id") Long id, @JsonProperty("ts") Long ts, @JsonProperty("ty") String ty, @JsonProperty("pl") String pl) {

		this.id = id;
		this.ts = ts;
		this.ty = ty;
		this.pl = pl;
	}

	public Long getId() {

		return id;
	}

	public Long getTs() {

		return ts;
	}

	public String getTy() {

		return ty;
	}

	public String getPl() {

		return pl;
	}

	@Override
	public String toString() {

		String decodedPl = new String(Base64.getDecoder().decode(pl));
		return "BidDto{" + "id=" + id + ", ts=" + ts + ", ty='" + ty + ", pl='" + decodedPl + '}';
	}
}
