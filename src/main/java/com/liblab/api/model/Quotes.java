package com.liblab.api.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quotes {

	@SerializedName("docs")
	@Expose
	private List<Quote> quotes;

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

}