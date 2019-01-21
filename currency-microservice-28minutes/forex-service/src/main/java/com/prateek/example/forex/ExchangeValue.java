package com.prateek.example.forex;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ExchangeValue {

	@Id
	private Long id;

	@Column(name = "currency_from")
	private String from;

	@Column(name = "currency_to")
	private String to;

	private BigDecimal conversionMultiple;
	private int port;
}