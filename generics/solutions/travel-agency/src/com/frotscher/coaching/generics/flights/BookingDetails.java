package com.frotscher.coaching.generics.flights;

public class BookingDetails {

	private String customerName;
	private FlightDetails flight;
	
	public BookingDetails(String customerName, FlightDetails flight) {
		this.customerName = customerName;
		this.flight = flight;
	}

	public String getCustomerName() {
		return customerName;
	}

	public FlightDetails getFlight() {
		return flight;
	}
}