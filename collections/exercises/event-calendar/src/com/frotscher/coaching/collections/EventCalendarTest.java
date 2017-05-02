package com.frotscher.coaching.collections;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.Test;

public class EventCalendarTest {

	@Test
	public void testEventCalendar() {
		EventCalendar cal = new EventCalendarSolution();
		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 24, 10, 0, 0), "Coaching Group D"));
		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 24, 13, 0, 0), "Coaching Group C"));
		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 24, 15, 0, 0), "Coaching Group E"));

		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 25, 10, 0, 0), "Coaching Group G"));
		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 25, 13, 0, 0), "Coaching Group B"));
		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 25, 15, 0, 0), "Coaching Group AF"));

		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 27, 10, 0, 0), "Coaching Group E"));
		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 27, 13, 0, 0), "Coaching Group D"));
		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 27, 15, 0, 0), "Coaching Group C"));

		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 28, 10, 0, 0), "Coaching Group B"));
		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 28, 13, 0, 0), "Coaching Group AF"));
		cal.addEvent(new Event(LocalDateTime.of(2017, 4, 28, 15, 0, 0), "Coaching Group G"));
		
		Collection<Event> eventsAfter = cal.getEventsAfter(LocalDateTime.of(2017, 4, 24, 0, 0, 0));
		Collection<Event> eventsBefore = cal.getEventsBefore(LocalDateTime.of(2017, 4, 26, 0, 0, 0));
		Collection<Event> eventsBetween = cal.getEventsBetween(LocalDateTime.of(2017, 4, 25, 0, 0, 0),
				                                               LocalDateTime.of(2017, 4, 28, 12, 0, 0));
//		assertNotNull(eventsAfter);
//		assertEquals(12, eventsAfter.size());
//
//		assertNotNull(eventsBefore);
//		assertEquals(6, eventsBefore.size());
//		
//		assertNotNull(eventsBetween);
//		assertEquals(7, eventsBetween.size());
	}
}
