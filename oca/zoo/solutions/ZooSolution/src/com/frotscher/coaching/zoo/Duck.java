package com.frotscher.coaching.zoo;


public class Duck extends Bird implements Swimming, Walking, Flying  {

	public Duck() {
		super("Duck");
	}

	@Override
	public void walk() {
		System.out.println("Duck walks. Speed: " + Walking.getSpeed());
	}

	@Override
	public void swim() {
		System.out.println("Duck swims...");
	}
	
	@Override 
	public void fly() {
		System.out.println("Duck flies. Speed: " + Flying.getSpeed());
	}
}