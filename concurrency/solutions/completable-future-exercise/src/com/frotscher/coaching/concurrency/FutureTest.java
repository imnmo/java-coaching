package com.frotscher.coaching.concurrency;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.logging.Level.INFO;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import org.junit.Test;

public class FutureTest {

	private final static Logger logger = Logger.getLogger(FutureTest.class.getSimpleName());

	@Test
	public void testFuturePull() throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(5);
		QuoteService quoteService = new QuoteService();

		Future<Integer> futureQuoteFromA =  
			    executor.submit(() -> quoteService.getQuoteFromSupplier("A")); 
		
		Future<Integer> futureQuoteFromB =  
			    executor.submit(() -> quoteService.getQuoteFromSupplier("B")); 

		// do other tasks for some time

		logger.log(INFO, "Waiting for quote from A");
		Integer quoteFromA = futureQuoteFromA.get();

		logger.log(INFO, "Waiting for quote from B");
		Integer quoteFromB = futureQuoteFromB.get();

		quoteService.calculateAveragePrice(quoteFromA, quoteFromB);
	}	

	@Test
	public void testFuturePoll() throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(5);
		QuoteService quoteService = new QuoteService();

		Future<Integer> futureQuoteFromA =  
			    executor.submit(() -> quoteService.getQuoteFromSupplier("A")); 

		Future<Integer> futureQuoteFromB =  
			    executor.submit(() -> quoteService.getQuoteFromSupplier("A")); 
		
		Map<String, Integer> quotes = new HashMap<>();
		while(quotes.size() < 2) {
			try {
				quotes.put("A", futureQuoteFromA.get(500, MILLISECONDS));
			} catch (TimeoutException e) {
				logger.log(INFO, "Quote not yet available from supplier A.");
			}

			try {
				quotes.put("B", futureQuoteFromB.get(500, MILLISECONDS));
			} catch (TimeoutException e) {
				logger.log(INFO, "Quote not yet available from supplier B.");
			}
			
			logger.log(INFO, "Doing something useful while waiting...");
			Sleeper.sleep(1);
		}
		quoteService.calculateAveragePrice(quotes.get("A"), quotes.get("B"));
	}	
}