package com.frotscher.coaching.optional;

import java.util.Optional;

public class Soundcard {

	private Optional<USB> usb;
	
	public Soundcard() {
		this.usb = Optional.empty();
	}
	
	public Soundcard(USB usb) {
		// Optional.of(usb) could cause NPE if usb==null
		this.usb = Optional.ofNullable(usb);
	}
	
	public Optional<USB> getUSB() {
		return usb;
	}
}
