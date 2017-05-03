package com.frotscher.coaching.zoo.exceptions;

public class WaterTooColdException extends SwimmingRefusedException {

	public WaterTooColdException() {
		super();
	}

	public WaterTooColdException(String message) {
		super(message);
	}
}