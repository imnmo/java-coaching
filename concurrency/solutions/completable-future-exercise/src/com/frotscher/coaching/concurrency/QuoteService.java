package com.frotscher.coaching.concurrency;

import java.util.Random;
import  static java.util.logging.Level.*;
import java.util.logging.Logger;

/**
 * Utility class for testing. Simulates a service that retrieves
 * quotes from suppliers, and calculates or displays prices.
 */
public class QuoteService {

	private final static Logger logger = Logger.getLogger(QuoteService.class.getSimpleName());

	protected final Integer MAX_PROCESSING_TIME_SECONDS = 10;
	protected final Integer MAX_WAITING_TIME_SECONDS = 10; // reduce to create occasional errors

	protected Random random = new Random();

	
	public Integer getQuoteFromSupplier(String supplierName) {
		logger.log(INFO, "Retrieving quote from supplier " + supplierName + "...");
		
		int processingTimeInSeconds = random.nextInt(MAX_PROCESSING_TIME_SECONDS);
		if (processingTimeInSeconds > MAX_WAITING_TIME_SECONDS) {
			throw new RuntimeException("Retrieval of quote from supplier " + supplierName + "takes too long.");
		}
		
		Sleeper.sleep(processingTimeInSeconds);
		int quote = 50 + random.nextInt(25);
		logger.log(INFO, "Returning quote from supplier " + supplierName + ": " + quote);
		return quote;
	}

	public Integer handleQuoteRetrievalIssue(Throwable t) {
		logger.log(SEVERE, "Something went wrong.", t);
		return -1;
	}

	public Integer computeSalePrice(Integer quote) {
		logger.log(INFO, "Computing sale price for quote " + quote);

		// negative quote means that an error occurred
		if (quote!=-1) { 
			Sleeper.sleep(3);
			return quote + 5;
		} else {
			return quote;
		}
	}

	public Integer calculateAveragePrice(Integer firstResult, Integer secondResult) {
		logger.log(INFO, "Processing both results...");
		Sleeper.sleep(2);
		int avgPrice = (firstResult+secondResult)/2;
		logger.log(INFO, String.format("Received quotes %d and %d. The average price is %d.", firstResult, secondResult, avgPrice));
		return avgPrice;
	}

	public void displaySalePrice(Integer salePrice) {
		logger.log(INFO, "Sale price: " + salePrice);
	}

	public void displaySalePriceOrError(Integer salePrice, Throwable t) {
		if (t==null) {
			logger.log(INFO, "Sale price: " + salePrice);
		} else {
			logger.log(SEVERE, "Unable to retrieve quote:" + t.getMessage());
		}
	}
		
	public void sendEmailNotification() {
		logger.log(INFO, "Sending email notification...");
		Sleeper.sleep(1);
	}
}