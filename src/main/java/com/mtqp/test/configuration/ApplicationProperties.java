package com.mtqp.test.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

	private int executionInterval;

	public int getExecutionInterval() {

		return executionInterval;
	}

	public void setExecutionInterval(int executionInterval) {

		this.executionInterval = executionInterval;
	}
}
