package com.frotscher.coaching.zoo;

public class Lion extends Mammal implements Walking {

	public Lion() {
		super("Lion");
	}

	@Override
	public void eat() {
		System.out.println("Lion eats a big chunk of meat...");
	}

    @Override
	public void walk() {
		System.out.println("Lion walks...");
	}
}