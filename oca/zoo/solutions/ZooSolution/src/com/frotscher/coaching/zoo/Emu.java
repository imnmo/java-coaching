package com.frotscher.coaching.zoo;

public class Emu extends Bird implements Walking {

	public Emu() {
		super("Emu");
	}

	@Override
	public void walk() {
		System.out.println("Emu walks...");
	}
}
