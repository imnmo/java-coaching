package com.frotscher.coaching;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TrainingTest {

	@Test
	public void testGetTrainees() {
		Training training = new Training();
		List<String> trainees = training.getTrainees();
		
		assertNotNull(trainees);
		assertTrue(trainees.contains("Uwe Meier"));
		assertFalse(trainees.contains("Thilo"));
		
		for (String trainee : trainees) {
			System.out.println(trainee);
		}
		
		trainees.stream().forEach(System.out::println);
	}
	
	@Test
	public void testGetTraineesAsPersons() {
		Training training = new Training();
		List<Person> trainees = training.getTraineesAsPersons();
		
		assertNotNull(trainees);
		
		trainees.stream().forEach(System.out::println);
		
		trainees.stream().map(p -> p.getName()).forEach(System.out::println);

	}

}
