package com.frotscher.coaching.zoo;

public interface Flying {

	public default void fly() {
		System.out.println("Animal flies...");
	};
	
	public static int getSpeed() {
		return 20;
	}
}
