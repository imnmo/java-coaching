package com.frotscher.coaching.generics.flights;

import java.time.LocalDate;

public class FlightSearch {

	private String origin;
	private String destination;
	private LocalDate date;
	
	public FlightSearch(String origin, String destination, LocalDate date) {
		this.origin = origin;
		this.destination = destination;
		this.date = date;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public LocalDate getDate() {
		return date;
	}
}