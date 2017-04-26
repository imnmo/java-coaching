package com.frotscher.coaching.optional;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

public class ComputerTest {
	
	@Test
	public void testIfPresent() {
		Soundcard soundcardWithUSB = new Soundcard(new USB("3.0"));
		Soundcard soundcardWithoutUSB = new Soundcard();
		
		Optional<USB> optionalUsb = soundcardWithUSB.getUSB();
		// not recommended - no advantage over check for null...
//		if (usb.isPresent()) {
//			System.out.println(usb.get().getVersion());
//		}
		// better:
		optionalUsb.ifPresent(u -> System.out.println(u.getVersion()));

		
		soundcardWithoutUSB.getUSB().ifPresent(
				usb -> System.out.println(usb.getVersion())); // no NPE!
	}

	@Test
	public void testOrElse() {
		Soundcard soundcardWithUSB = new Soundcard(new USB("3.0"));
		Soundcard soundcardWithoutUSB = new Soundcard();
		
		System.out.println(soundcardWithUSB.getUSB().orElse(new USB("no USB")).getVersion());
		System.out.println(soundcardWithoutUSB.getUSB().orElse(new USB("no USB")).getVersion());
	}

	@Test
	public void testMap() {
		Computer computer = new Computer(new Soundcard(new USB("3.0")));
		
		String usbVersion = computer.getSoundcard()
									// required because of Optional<Optional<USB>>
                                    .flatMap(Soundcard::getUSB) 
                                    .map(USB::getVersion)
                                    .orElse("UNKNOWN");
		assertEquals("3.0", usbVersion);
	}

	@Test
	public void testFilter() {
		Computer computer1 = new Computer(new Soundcard(new USB("3.0")));
		Computer computer2 = new Computer(new Soundcard(new USB("2.0")));
		Computer computer3 = new Computer(new Soundcard());

		Stream.of(computer1, computer2, computer3)
		      .forEach(c -> c.getSoundcard()
                             .flatMap(Soundcard::getUSB)
                             .filter(usb -> "3.0".equals(usb.getVersion()))
                             .ifPresent(usb -> System.out.println(usb.getVersion() + " ok"))
		    		  );
	}
}
