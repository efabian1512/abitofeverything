package com.naifer.wigsshop.wigsshopping.generator;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class OrderIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = 1L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		
		Instant now = Instant.now();
		long milliseconds = now.toEpochMilli();
	
		String millisecondsString = String.valueOf(milliseconds);
		String timestamp = millisecondsString.substring(0,10);
		String id = "ORD-"+ timestamp + String.valueOf(UUID.randomUUID()).substring(0,4);

		return id.toUpperCase();
	}

	
}
