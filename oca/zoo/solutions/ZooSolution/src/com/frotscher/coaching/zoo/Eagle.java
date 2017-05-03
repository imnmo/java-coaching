package com.frotscher.coaching.zoo;

public class Eagle extends Bird implements Flying, Walking {

	public Eagle() {
		super("Eagle");
	}

	@Override
	public void walk() {
		System.out.println("Eagle walks. Speed: " + Walking.getSpeed());
	}
	
	@Override
	public void fly() {
		System.out.println("Eagle flies. Speed: " + Flying.getSpeed());
	}
}