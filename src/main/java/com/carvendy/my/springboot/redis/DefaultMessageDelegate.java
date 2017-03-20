package com.carvendy.my.springboot.redis;

import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DefaultMessageDelegate implements MessageDelegate {

	@Override
	public void handleMessage(String message) {
		System.out.println("in"+message);
	}

	@Override
	public void handleMessage(Map message) {
		System.out.println("in"+message);
	}

	@Override
	public void handleMessage(byte[] message) {
		System.out.println("in"+message);
	}

	@Override
	public void handleMessage(Serializable message) {
		System.out.println("in"+message);
	}

	@Override
	public void handleMessage(Serializable message, String channel) {
		
	}
	// implementation elided for clarity...
}