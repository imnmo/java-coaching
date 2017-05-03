package com.frotscher.coaching.zoo;

public abstract class Animal {

	protected String name;
	
	public Animal(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void eat() {
		System.out.println(name + " eats...");
	}
}
