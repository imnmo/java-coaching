package com.frotscher.coaching.zoo;

public abstract class Mammal extends Animal {

	public Mammal(String name) {
		super(name);
	}

	public void giveBirth(int numberOfChildren) {
		System.out.println(name + " giving birth to " + numberOfChildren + " children...");
	}
}