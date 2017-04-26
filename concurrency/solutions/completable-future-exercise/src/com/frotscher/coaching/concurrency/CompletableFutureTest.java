package com.frotscher.coaching.concurrency;

import static java.util.logging.Level.INFO;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.junit.Test;

public class CompletableFutureTest {

	private final static Logger logger = Logger.getLogger(CompletableFutureTest.class.getSimpleName());

	@Test
	public void testTwoStages() {
		
		QuoteService quoteService = new QuoteService();

		CompletableFuture<Void> futureAllStagesDone =  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("A")) 
                                 .exceptionally(quoteService::handleQuoteRetrievalIssue)
                                 .thenAccept(quoteService::displaySalePrice);
		
		// we could also use join(), but that is a blocking call, so we could
		// not do anything else while waiting
		
		while (!futureAllStagesDone.isDone()) {
			logger.log(INFO, "Doing something useful while waiting...");
			Sleeper.sleep(1);
		}
	}

	@Test
	public void testTwoStagesWithHandle() {
		
		QuoteService quoteService = new QuoteService();

		CompletableFuture<Void> futureAllStagesDone =  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("A")) 
			                     .handle((quote, t) -> { quoteService.displaySalePriceOrError(quote, t); return null; });
		
		// we could also use join(), but that is a blocking call, so we could
		// not do anything else while waiting

		while (!futureAllStagesDone.isDone()) {
			logger.log(INFO, "Doing something useful while waiting...");
			Sleeper.sleep(1);
		}
	}

	@Test
	public void testMoreStages() {
		
		QuoteService quoteService = new QuoteService();
		
		CompletableFuture<Void> futureAllStagesDone =  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("A")) 
								 .thenApply(quoteService::computeSalePrice)
								 .thenAccept(quoteService::displaySalePrice)
								 .thenRun(quoteService::sendEmailNotification);
		
		// we could also use join(), but that is a blocking call, so we could
		// not do anything else while waiting

		while (!futureAllStagesDone.isDone()) {
			logger.log(INFO, "Doing something useful while waiting...");
			Sleeper.sleep(1);
		}
		
		logger.log(INFO, "Done");
	}


	@Test
	public void testMoreStagesWithExceptionally() {
		
		QuoteService quoteService = new QuoteService();
		
		CompletableFuture<Void> futureAllStagesDone =  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("A"))
			                     .exceptionally(quoteService::handleQuoteRetrievalIssue)
								 .thenApply(quoteService::computeSalePrice)
								 .thenAccept(quoteService::displaySalePrice)
								 .thenRun(quoteService::sendEmailNotification);
		
		// we could also use join(), but that is a blocking call, so we could
		// not do anything else while waiting

		while (!futureAllStagesDone.isDone()) {
			logger.log(INFO, "Doing something useful while waiting...");
			Sleeper.sleep(1);
		}
		
		logger.log(INFO, "Done");
	}
	

	@Test
	public void testProcessEither() throws Exception {
		
		QuoteService quoteService = new QuoteService();

		CompletableFuture<Integer> futureQuoteFromA =  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("A"))
			                     .exceptionally(quoteService::handleQuoteRetrievalIssue);

		CompletableFuture<Integer> futureQuoteFromB =  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("B"))
			                     .exceptionally(quoteService::handleQuoteRetrievalIssue);

		CompletableFuture<Void> eitherProcessed = 
				futureQuoteFromA.applyToEither(futureQuoteFromB, quoteService::computeSalePrice) 
		                   .thenAccept(quoteService::displaySalePrice)
		                   .thenRun(quoteService::sendEmailNotification);

		// If we run just the above code, then the main thread completes before futures are complete... 		
		// Option 1: Do something useful while we are waiting for any of the futures to complete
		while (!eitherProcessed.isDone()) {
			logger.log(INFO, "Doing something useful while waiting...");
			Sleeper.sleep(1);
		}
		
		// Option 2: Just wait.
//		logger.log(INFO, "Waiting for async tasks to complete...");
//		eitherProcessed.join();
		// ------------------

		logger.log(INFO, "Done.");

//		assertEquals(50, futureQuoteFromA.get().intValue());
//		assertEquals(75, futureQuoteFromB.get().intValue());
	}
	
	@Test
	public void testProcessBoth() throws Exception {
		
		QuoteService quoteService = new QuoteService();

		CompletableFuture<Integer> futureQuoteFromA =  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("A"))
			                     .exceptionally(quoteService::handleQuoteRetrievalIssue);

		CompletableFuture<Integer> futureQuoteFromB =  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("B"))
			                     .exceptionally(quoteService::handleQuoteRetrievalIssue);

		CompletableFuture<Void> bothProcessed = 
				futureQuoteFromA.thenCombineAsync(futureQuoteFromB, quoteService::calculateAveragePrice)
				                .thenApply(quoteService::computeSalePrice)
				                .thenAccept(quoteService::displaySalePrice)
				                .thenRun(quoteService::sendEmailNotification);

		// If we run just the above code, then the main thread completes before futures are complete... 
		// Option 1: Do something useful while we are waiting for any of the futures to complete
		while (!bothProcessed.isDone()) {
			logger.log(INFO, "Doing something useful while waiting...");
			Sleeper.sleep(1);
		}
		
		// Option 2: Just wait.
//		logger.log(INFO, "Waiting for async tasks to complete...");
//		bothProcessed.join();
		// ------------------

		logger.log(INFO, "Done.");
	}

	@Test
	public void testWaitForAll() throws Exception {
		
		QuoteService quoteService = new QuoteService();

		ExecutorService executor = Executors.newFixedThreadPool(5);
		List<CompletableFuture<Integer>> futures = new ArrayList<>();
		
		futures.add(  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("A"), executor)
			                     .exceptionally(quoteService::handleQuoteRetrievalIssue)
        );
		futures.add(  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("B"), executor)
			                     .exceptionally(quoteService::handleQuoteRetrievalIssue)
		);
		futures.add(  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("C"), executor)
			                     .exceptionally(quoteService::handleQuoteRetrievalIssue)
		);
		futures.add(  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("D"), executor)
			                     .exceptionally(quoteService::handleQuoteRetrievalIssue)
		);
		futures.add(  
			    CompletableFuture.supplyAsync(() -> quoteService.getQuoteFromSupplier("E"), executor)
			                     .exceptionally(quoteService::handleQuoteRetrievalIssue)
		);	                     

		CompletableFuture<Void> allProcessed = 
				CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

		while (!allProcessed.isDone()) {
			logger.log(INFO, "Doing something useful while waiting...");
			Sleeper.sleep(1);
		}
		
		OptionalInt min = futures.stream().mapToInt(future -> future.getNow(Integer.MAX_VALUE)).min();

		assertTrue(min.getAsInt() >= 50);
		assertTrue(min.getAsInt() <= 75);
		
		logger.log(INFO, "Minimum quote was: " + min.getAsInt());
		logger.log(INFO, "Done.");
	}
}