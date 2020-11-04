package com.mtqp.test.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtqp.test.dto.BidDto;
import com.mtqp.test.dto.BidWrapperDto;
import com.mtqp.test.exception.JsonConvertingException;
import com.mtqp.test.service.ConverterService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConverterServiceImpl implements ConverterService {

	private static final String JSON_SOURCE_PATH = "/bids/bids.json";
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Override
	public List<BidDto> readJson() {

		try (InputStream sourceInputStream = new ClassPathResource(JSON_SOURCE_PATH).getInputStream()) {
			var wrappers = Arrays.asList(OBJECT_MAPPER.readValue(sourceInputStream, BidWrapperDto[].class));
			return wrappers.stream().map(BidWrapperDto::getBid).collect(Collectors.toList());
		} catch (IOException e) {
			throw new JsonConvertingException(e);
		}
	}
}
