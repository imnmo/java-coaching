package com.frotscher.coaching.optional;

import java.util.Optional;

public class Computer {

	private Optional<Soundcard> soundcard;
	
	public Computer() {
		this.soundcard = Optional.empty();
	}
	
	public Computer(Soundcard soundcard) {
		// Optional.of(soundcard) could cause NPE if soundcard==null
		this.soundcard = Optional.ofNullable(soundcard);
	}
	
	public Optional<Soundcard> getSoundcard() {
		return soundcard;
	}
}
