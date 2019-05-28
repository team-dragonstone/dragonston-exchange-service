package com.training.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ExchangeValue {
	@Id
	private Long id;
	@Column(name="currency_from")
	private String from;
	@Column(name="currency_to")
	private String to;
	@Column(name="conversion_rate")
	private BigDecimal conversionRate;
	private int port;
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(BigDecimal conversionrate) {
		this.conversionRate = conversionrate;
	}
	public ExchangeValue( Long id, String from, String to, BigDecimal conversionrate) {
		super();
		this.from = from;
		this.to = to;
		this.id = id;
		this.conversionRate = conversionrate;
	}
	
	protected ExchangeValue () {
		
	}
	@Override
	public String toString() {
		return "ExchangeValue [id=" + id + ", from=" + from + ", to=" + to + ", conversionRate=" + conversionRate
				+ ", port=" + port + "]";
	}
}
