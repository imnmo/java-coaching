package com.frotscher.coaching.generics.flights;

public class BookingConfirmation {

	private BookingDetails bookingDetails;
	private long bookingNumber;
	
	public BookingConfirmation(BookingDetails bookingDetails, long bookingNumber) {
		this.bookingDetails = bookingDetails;
		this.bookingNumber = bookingNumber;
	}

	public BookingDetails getBookingDetails() {
		return bookingDetails;
	}

	public long getBookingNumber() {
		return bookingNumber;
	}
	
	@Override
	public String toString() {
		return "Confirmation for booking number " + bookingNumber;
	}
}