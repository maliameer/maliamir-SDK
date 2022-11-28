package com.liblab.api;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.liblab.rest.RESTServices;

import com.liblab.api.model.Quote;

public class QuoteManagerTest {

	private static final String QUOTE_ID = "5cd96e05de30eff6ebcce7e9";
	private static final String QUOTE_DIALOG = "Deagol!";

	private QuoteManager quoteManager;

	@Before
	public void setup() {
		quoteManager = new QuoteManager();
	}

	@Test
	public void testQuotes() {
		int quotesCount = quoteManager.getQuotes().size();
		assertEquals("Unexpected Quotes Count", 1000, quotesCount);
	}

	@Test
	public void testQuoteById() {
		Quote quote = quoteManager.getQuote(QUOTE_ID);
		assertNotNull("Quote cannot be NULL", quote);
		assertEquals("Unexpected Quote Dialog", QUOTE_DIALOG, quote.getDialog());
	}
	
	@Test
	public void testQuotesWithCriteria() {		
		List<Quote> quotes = quoteManager.getMovies("_id=" + QUOTE_ID, 
				"sort=dialog:" + RESTServices.DESC, 10, 1, 0);
		assertEquals("Unexpected Quotes Count", 1, quotes.size());
		assertEquals("Unexpected Quote Dialog", QUOTE_DIALOG, quotes.get(0).getDialog());
	}


}