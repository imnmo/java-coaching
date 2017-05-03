package com.frotscher.coaching.collections;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;



public class EventCalendarSolution implements EventCalendar {
    
    
    Map<LocalDateTime, String> eventCalendar = new TreeMap<>();

	@Override
	public void addEvent(Event event) {
	    eventCalendar.put(event.getDateTime(), event.getName());
	}

	@Override
	public Collection<Event> getEventsBefore(LocalDateTime dateTime) {

        java.util.List<Event> eventsBefore = new ArrayList<>();

        eventsBefore = eventCalendar.entrySet()
                .stream()
                .map(p -> new Event(p.getKey(), p.getValue()))
                .filter(item -> item.getDateTime().isBefore(dateTime))
                .collect(Collectors.toList());
	    return eventsBefore;
	}
	
	/**
	 * Do the before and after and then proceed for logic on between should work fine
	 */

	@Override
	public Collection<Event> getEventsAfter(LocalDateTime dateTime) {
	    
	    java.util.List<Event> eventsAfter = new ArrayList<>();

	    eventCalendar.forEach((k,v)->{
	        Event myEvent = new Event(k, v);
	        eventsAfter.add(myEvent);
        });
	    return eventsAfter;

	}

	@Override
	public Collection<Event> getEventsBetween(LocalDateTime begin, LocalDateTime end) {
	    
	    java.util.List<Event> eventsBetween = new ArrayList<>();
	    
	    eventsBetween = eventCalendar.entrySet()
	                                 .stream()
	                                 .map(p -> new Event(p.getKey(), p.getValue()))
	                                 .filter(item -> item.getDateTime().isAfter(begin) && item.getDateTime().isBefore(end))
	                                 .collect(Collectors.toList());
	    return eventsBetween;
	}
	
	
	
}
