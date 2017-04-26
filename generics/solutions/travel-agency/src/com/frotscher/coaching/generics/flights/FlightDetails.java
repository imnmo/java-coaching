package com.frotscher.coaching.generics.flights;

public class FlightDetails {

	private String origin;
	private String destination;
	private String flightNumber;
	private double rate;

	public FlightDetails(String origin, String destination, String flightNumber, double rate) {
		this.origin = origin;
		this.destination = destination;
		this.flightNumber = flightNumber;
		this.rate = rate;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public double getRate() {
		return rate;
	}
	
	@Override
	public String toString() {
		return origin + "-" + destination + " => flight " + flightNumber + ", rate " + rate;
	}
}
