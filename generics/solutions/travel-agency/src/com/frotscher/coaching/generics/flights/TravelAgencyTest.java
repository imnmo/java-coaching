package com.frotscher.coaching.generics.flights;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TravelAgencyTest {

	@Test
	public void testIndividualTasks() {
		FlightSearch searchCriteria = new FlightSearch("FRA", "LON", LocalDate.of(2017, Month.JULY, 25));
		FlightSearchTask searchTask = new FlightSearchTask(searchCriteria);
		FlightDetails flightDetails = searchTask.execute();
		
		assertNotNull(flightDetails);
		assertEquals("FRA", flightDetails.getOrigin());
		assertEquals("LON", flightDetails.getDestination());
		assertEquals("LH123", flightDetails.getFlightNumber());
		assertEquals(250.00, flightDetails.getRate(), 0.00);
		
		BookingDetails bookingDetails = new BookingDetails("Thilo", flightDetails);
		FlightBookingTask bookingTask = new FlightBookingTask(bookingDetails); 
		BookingConfirmation confirmation = bookingTask.execute();
		
		assertNotNull(confirmation);
		assertEquals(1234567898L, confirmation.getBookingNumber());
	}
	
	@Test
	public void testListsOfTasksWithBookingEngine() {

		FlightSearch searchCriteria1 = new FlightSearch("FRA", "LON", LocalDate.of(2017, Month.JULY, 25));
		FlightSearch searchCriteria2 = new FlightSearch("FRA", "LON", LocalDate.of(2017, Month.JULY, 26));
		FlightSearch searchCriteria3 = new FlightSearch("FRA", "LON", LocalDate.of(2017, Month.JULY, 27));

		FlightDetails flightDetails1 = new FlightDetails("FRA", "LON", "LH123", 500.00);
		BookingDetails bookingDetails1 = new BookingDetails("Thilo", flightDetails1);	
				
		
//		List<Task<FlightSearch, FlightDetails>> tasksOfSameType = new ArrayList<>(); // does not compile below
		List<Task<?, ?>> tasksOfSameType = new ArrayList<>();
		tasksOfSameType.add(new FlightSearchTask(searchCriteria1));
		tasksOfSameType.add(new FlightSearchTask(searchCriteria2));
		tasksOfSameType.add(new FlightSearchTask(searchCriteria3));

		BookingEngine engine = new BookingEngine();

		List<?> resultsOfSameType = engine.executeTasksSync(tasksOfSameType);
		assertNotNull(resultsOfSameType);
		assertEquals(3, resultsOfSameType.size());
		
		// => Issue with returned list of results: concrete result type is unknown. Can't do much with it...
		// => Potential solution: All result types need to implement a common interface 
		//    or extend a common subclass.
		resultsOfSameType.stream()
		                 .map(result -> result.toString())
		                 .forEach(System.out::println);
		
		
		/////////////////////////////
		
		List<Task<?, ?>> tasksOfDifferentTypes = new ArrayList<>();
		tasksOfDifferentTypes.add(new FlightSearchTask(searchCriteria1));
		tasksOfDifferentTypes.add(new FlightSearchTask(searchCriteria2));
		tasksOfDifferentTypes.add(new FlightSearchTask(searchCriteria3));
		tasksOfDifferentTypes.add(new FlightBookingTask(bookingDetails1));
		
		List<?> resultsOfDifferentType = engine.executeTasksSync(tasksOfDifferentTypes);
		assertNotNull(resultsOfDifferentType);
		assertEquals(4, resultsOfDifferentType.size());

		// => Issue with returned list of results: concrete result type is unknown. Can't do much with it
		// => Potential solution: All result types need to implement a common interface 
		//    or extend a common subclass.
		resultsOfDifferentType.stream()
		                      .map(result -> result.toString())
		                      .forEach(System.out::println);
	}
}
