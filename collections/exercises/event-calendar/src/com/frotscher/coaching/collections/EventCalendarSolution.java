package com.frotscher.coaching.collections;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class EventCalendarSolution implements EventCalendar {
    
    
    Map<LocalDateTime, String> eventCalendar = new TreeMap<>();

	@Override
	public void addEvent(Event event) {
	    eventCalendar.put(event.getDateTime(), event.getName());
	}

	@Override
	public Collection<Event> getEventsBefore(LocalDateTime dateTime) {

//	    eventCalendar.forEach((k,v)->{
//	        if(eventCalendar.containsKey(dateTime)){
//	            return eventCalendar.;
//	        }
//	    });
        return null;
	}

	@Override
	public Collection<Event> getEventsAfter(LocalDateTime dateTime) {
	    
	    Set<Event> events = new HashSet<>();
	    
	    if(eventCalendar.containsKey(dateTime)){
	        eventCalendar.forEach((k,v)->{
	            //The stumbling part is the how we get EVENT from its characteristics.

            });
	        
	    }
	    

	    
	    return java.util.Collections.emptyList();

	}

	@Override
	public Collection<Event> getEventsBetween(LocalDateTime begin, LocalDateTime end) {
		// TODO Auto-generated method stub
	    return java.util.Collections.emptyList();
	}
}
