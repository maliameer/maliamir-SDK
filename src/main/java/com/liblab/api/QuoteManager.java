package com.liblab.api;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;

import retrofit2.Call;

import com.liblab.rest.RESTCommunication;
import com.liblab.rest.RESTServices;

import com.liblab.api.model.Quote;
import com.liblab.api.model.Quotes;

public class QuoteManager extends Manager {

	private static Logger logger = Logger.getLogger(QuoteManager.class.getName());

	public String getMainEndpoint() {
		return RESTServices.QUOTE;
	}

	public List<Quote> getMovies(String queryString, String sort, int limit, int page, int offset) {
		Quotes quotes = (Quotes)super.get(Quotes.class, queryString, sort, limit, page, offset);
		return quotes.getQuotes();
	}

	public List<Quote> getQuotes() {
		
		Call<Quotes> quotesRestCall = RESTCommunication.getRESTServicesInstance().getQuotes(Manager.getBearerAuthorization());
		try {
			return quotesRestCall.execute().body().getQuotes();
		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Quotes due to - " + ioe.getMessage());
			return null;
		}

	}

	public Quote getQuote(String id) {
		
		Call<Quotes> quotesRestCall = RESTCommunication.getRESTServicesInstance().getQuote(Manager.getBearerAuthorization(), id);
		try {
			List<Quote> quotes = quotesRestCall.execute().body().getQuotes();
			return quotes != null && quotes.size() > 0 ? quotes.get(0) : null;
		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Quote due to - " + ioe.getMessage());
			return null;
		}
		
	}
}