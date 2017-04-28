package com.frotscher.coaching.collections;

import java.time.LocalDateTime;
import java.util.Collection;

public interface EventCalendar {

	public abstract void addEvent(Event event);
	
	public abstract Collection<Event> getEventsBefore(LocalDateTime dateTime);
	
	public abstract Collection<Event> getEventsAfter(LocalDateTime dateTime);

	public abstract Collection<Event> getEventsBetween(LocalDateTime begin, LocalDateTime end);
}
