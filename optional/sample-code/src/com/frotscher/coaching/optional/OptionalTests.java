package com.frotscher.coaching.optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.stream.Stream;

import org.junit.Test;

public class OptionalTests {

	/**
	 * Why was Optional introduced?
	 */
	@Test
	public void testReduceWithIdentity() {
		
		// reduce method with identity value on non-empty stream
		int sum = Stream.of(-2, -1, 0, 1, 2)
                 	.reduce(0, (i, j) -> i + j);

		System.out.println("sum is: " + sum); 
		assertEquals(0, sum);

		
		// reduce method with identity value on non-empty stream
		int sum2 = Stream.of(-2, -1, 0, 1, 2)
					.filter(i -> i > 10)
					.reduce(0, (i, j) -> i + j);

		System.out.println("sum2 is: " + sum2); 
		assertEquals(0, sum2);

		// => how to distinguish between sum=0 and empty stream?
		// => same issue for several Stream methods: reduce, min, max, findFirst, findAny
	}	
	
	
	@Test
	public void testReduceWithOptional() {
		
		// reduce method with NO identity value on non-empty stream
		Optional<Integer> sum = Stream.of(-2, -1, 0, 1, 2)
                 					.reduce((i, j) -> i + j);

		assertTrue(sum.isPresent());
		assertEquals(Integer.valueOf(0), sum.get());
		
		if (sum.isPresent())                                   
			System.out.println("sum is: " + sum.get());
		else
			System.out.println("stream was empty"); 
		
		
		// reduce method with NO identity value on non-empty stream
		Optional<Integer> sum2 = Stream.of(-2, -1, 0, 1, 2)
									.filter(i -> i > 10)
									.reduce((i, j) -> i + j);

		assertFalse(sum2.isPresent());

		if (sum2.isPresent())                                   
			System.out.println("sum is: " + sum2.get());
		else
			System.out.println("stream was empty"); 
	}

	
	/*
	 * How Optional works...
	 */
	
	@Test(expected=NoSuchElementException.class)
	public void testOptionalEmpty() {
		Optional<String> optStr = Optional.empty();
		assertFalse(optStr.isPresent());
		// calling get() on Optional with no value present throws NoSuchElementException
		optStr.get();
	}
	
	@Test
	public void testOptionalWithValue() {
		Optional<String> optStr = Optional.of("Hello World");
		assertTrue(optStr.isPresent());
		assertEquals("Hello World", optStr.get());
	}
	
	@Test(expected=NullPointerException.class)
	public void testOptionalWithNullValue1() {
		// passing null to of() throws NullPointerException
		Optional<String> optStr = Optional.of(null);
	}
	
	// ==> Use of() only if you are certain that the value cannot be NULL
	// ==> If the value *is* NULL, then you get the advantage of "fail fast" 
	
	@Test
	public void testOptionalWithNullValue2() {
		// passing null to ofNullable() does not throw NullPointerException
		String str = null;
		Optional<String> optStr = Optional.ofNullable(str);
		assertFalse(optStr.isPresent());
		// calling get() would throw a NoSuchElementException, like above...
		// => Optional can never wrap "null", get() will never return null...
	}
	
	// ==> Use ofNullable() only if you are not certain if the value might be NULL
	
	
	@Test(expected=NullPointerException.class)
	public void testStreamThatContainsNull() {
		Optional<String> opt = Stream.of(null, "hello", "world").findFirst();
		// the above throws a NullPointerException inside Stream API
		// => this is one of the issues of Optional that are sometimes criticized 
	}

	
	/*
	 * If the value is not empty...
	 */
	@Test
	public void testAddingConsumerWhenOptionalIsNotEmpty() {
//		int sum = Stream.of(1, 2, 3, 4, 5)
//		      .reduce((i, j) -> i + j)
//		      .ifPresent (sum -> System.out.println("sum is: " + sum)); 
	}
	

	/*
	 * If the value is empty...
	 */
	@Test
	public void testOrElse() {
		int sum = Stream.of(1, 2, 3, 4, 5)
				        .filter(i -> (i > 10))
				        .reduce((i, j) -> i + j)
				        .orElse(0); 
		assertEquals(0, sum);
	}

	@Test
	public void testOrElseGet() {
		int sum = Stream.of(1, 2, 3, 4, 5)
				        .filter(i -> (i > 10))
				        .reduce((i, j) -> i + j)
				        .orElseGet(this::getDefaultValue); 
		assertEquals(1234, sum);
	}

	public int getDefaultValue() {
		return 1234;
	}
	
	@Test(expected=RuntimeException.class)
	public void testOrElseThrow() {
		int sum = Stream.of(1, 2, 3, 4, 5)
				        .filter(i -> (i > 10))
				        .reduce((i, j) -> i + j)
				        .orElseThrow( () -> new RuntimeException("Stream is empty")); 
	}
	
	
	/* 
	 * filter(): The Optional class contains a filter() method that expects a Predicate and 
	 * returns an Optional back if the Predicate is true
	 */
	@Test
	public void testFilterIfValueIsNotPresent() {
		Optional<String> stringOptionalEmpty = Optional.empty();
		stringOptionalEmpty.filter(s -> s.length()==10)
		                   .ifPresent(s -> System.out.println(s.length() + " is ok!" ));
	 }
	
	@Test
	public void testFilterIfValueDoesNotPassFilter() {
		Optional<String> stringOptionalLong = Optional.of("123456789012345");
		stringOptionalLong.filter(s -> s.length()==10)
		                  .ifPresent(s -> System.out.println(s.length() + " is ok!" ));
	}
	
	@Test
	public void testFilterIfValueDoesPassFilter() {
		Optional<String> stringOptionalOk = Optional.of("1234567890");
		stringOptionalOk.filter(s -> s.length()==10)
		                .ifPresent(s -> System.out.println(s.length() + " is ok!" ) );
	}

	
	/*
	 * map():  “maps” or converts an Optional to another Optional using a Function as parameter. 
	 * The mapping is only executed, if the result of the past Function is not null.
	 */
	@Test
	public void testNonEmptyStringMapsToItsLength() {
		Optional<String> stringOptional = Optional.of("loooooooong string");
		Optional<Integer> sizeOptional = stringOptional.map(String::length); //map from Optional to Optional
		int size = sizeOptional.orElse(0);
		assertEquals(18, size);
	}
	
	@Test
	public void testEmptyStringMapsToLengthZero() {
		Optional<String> stringOptionalNull = Optional.ofNullable( null );
		Optional<Integer> sizeOptionalNull = stringOptionalNull.map( s -> s.length() ); // we can use Lambdas as we want
		int size = sizeOptionalNull.orElse(0);
		assertEquals(0, size);
	}	

	// Why not use Optional as a method parameter?
	// => It results in APIs that are harder to use for the caller:
	// => better use overloaded methods if some params are optional
	
	public void doIt(String name, OptionalInt i, OptionalLong l) {}

	public void callIt() {
		doIt("djflksfjls", OptionalInt.of(1), OptionalLong.of(2L));
	}
	
	
	
}