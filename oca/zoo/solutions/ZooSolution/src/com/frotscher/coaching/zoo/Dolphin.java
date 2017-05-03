package com.frotscher.coaching.zoo;

public class Dolphin extends Mammal implements Swimming {

	public Dolphin() {
		super("Dolphin");
	}

	@Override
	public void swim() {
		System.out.println("Dolphin swims...");		
	}
}
