package com.example.config.client.model;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("mails")
@RefreshScope
public class Mail implements Serializable{
	private static final long serialVersionUID = -7881822245518755945L;
	
	
	public String subject = "default subject";
	public String to = "default@mail.com";
	public String from = "default@mail.com";

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public String toString() {
		return "Mail [subject=" + subject + ", to=" + to + ", from=" + from + "]";
	}
	
	
}
