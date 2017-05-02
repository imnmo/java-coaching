package com.frotscher.coaching.collections;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {
	
	private LocalDateTime dateTime;
	private String name;
	
	public Event(LocalDateTime dateTime, String name) {
		Objects.requireNonNull(dateTime);
		Objects.requireNonNull(name);
		this.dateTime = dateTime;
        this.name = name;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Event) {
			Event otherEvent = (Event) other;
			return dateTime.equals(otherEvent.dateTime) && name.equals(otherEvent.name);
		} else {
			return false;
		}
	}
}
