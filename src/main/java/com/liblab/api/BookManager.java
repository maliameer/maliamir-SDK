package com.liblab.api;

import java.io.IOException;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import retrofit2.Call;

import com.liblab.common.CommonConstants;

import com.liblab.rest.RESTCommunication;
import com.liblab.rest.RESTServices;

import com.liblab.api.model.Book;
import com.liblab.api.model.Books;
import com.liblab.api.model.Chapter;
import com.liblab.api.model.Chapters;

/**
 * Manager class to call actions on /book end-point:
 * @author Muhammad Ali Amir
 */
public class BookManager extends Manager {

	private static Logger logger = Logger.getLogger(BookManager.class.getName());

	public String getMainEndpoint() {
		return RESTServices.BOOK;
	}

	/**
	 * Search List of Books as per provided parameters.
	 * @param queryString: Query String to hold param-value pair like name=Rings. It can be multiple pairs separate by '&amp;'
	 * @param sort: Like name:asc; id:desc etc.
	 * @param limit to limit number of records.
	 * @param page specific page.
	 * @param offset Record's offset.
	 * @return List of Book instances matching to the provided parameters.
	 */
	public List<Book> getBooks(String queryString, String sort, int limit, int page, int offset) {
		Books books = (Books)super.get(Books.class, queryString, sort, limit, page, offset);
		return books.getBooks();
	}

	public List<Book> getBooks() {
		
		Call<Books> booksRestCall = RESTCommunication.getRESTServicesInstance().getBooks();
		try {
			return booksRestCall.execute().body().getBooks();
		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Books due to - " + ioe.getMessage());
			return null;
		}

	}

	public Book getBook(String id) {
		
		Call<Books> booksRestCall = RESTCommunication.getRESTServicesInstance().getBook(id);
		try {
			List<Book> books = booksRestCall.execute().body().getBooks();
			return books != null && books.size() > 0 ? books.get(0) : null;
		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Book due to - " + ioe.getMessage());
			return null;
		}
		
	}

	public List<Chapter> getBookChapters(String id) {

		StringBuilder sb = new StringBuilder(Manager.getBaseUrl());
		sb.append(getMainEndpoint()).append(CommonConstants.URL_DELIMITER);
		sb.append(id).append(CommonConstants.URL_DELIMITER).append(RESTServices.CHAPTER);
		
		try {			
			
			String json = RESTCommunication.getJsonPayload(sb.toString(), Manager.getAccessToken());
			return ((Chapters)new Gson().fromJson(json, Chapters.class)).getChapters();

		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Books due to - " + ioe.getMessage());
			return null;
		}
		
	}

}