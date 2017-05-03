package com.frotscher.coaching.zoo;

public abstract class Bird extends Animal {

	public Bird(String name) {
		super(name);
	}
	
	public void growFeathers() {
		System.out.println(name + " is growing feathers");
	}
}