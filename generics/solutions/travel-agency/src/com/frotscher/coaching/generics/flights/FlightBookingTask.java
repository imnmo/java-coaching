package com.frotscher.coaching.generics.flights;

public class FlightBookingTask implements Task<BookingDetails, BookingConfirmation> {

	private BookingDetails bookingDetails;
	
	public FlightBookingTask(BookingDetails bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	
	@Override
	public BookingDetails getParameters() {
		return bookingDetails;
	}

	@Override
	public BookingConfirmation execute() {
		BookingConfirmation confirmation = 
				new BookingConfirmation(
						bookingDetails,
						1234567898L);
		return confirmation;
	}

}
