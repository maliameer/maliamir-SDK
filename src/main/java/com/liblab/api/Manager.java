package com.liblab.api;

import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;

import com.liblab.common.CommonConstants;

import com.liblab.rest.RESTCommunication;

public abstract class Manager {

	private static Logger logger = Logger.getLogger(Manager.class.getName());

	protected static Properties properties = new Properties();
	
	protected static final String ACCESS_TOKEN = "access.token";
	protected static final String BASE_URL = "base.url";
	
	static {
		
		try(InputStream resourceStream = Manager.class.getClassLoader().getResourceAsStream("sdk.properties")) {
			properties.load(resourceStream);
		} catch (IOException ioe) {
			logger.log(Level.SEVERE, "IOException while loading SDK Properties due to - " + ioe.getMessage());
		}
		
	}

	public static String getAccessToken() {
		return properties.getProperty(ACCESS_TOKEN);		
	}

	public static String getBaseUrl() {
		return properties.getProperty(BASE_URL);		
	}

	public static String getBearerAuthorization() {
		return RESTCommunication.BEARER + CommonConstants.SPACE + getAccessToken();		
	}
	
	protected String getSort(String sortField, String sortOrder) {
		return sortField + CommonConstants.COLON + sortOrder;
	}
	
	protected Object get(Class clazz, String queryString, String sort, int limit, int page, int offset) {
		
		StringBuilder sb = new StringBuilder(Manager.getBaseUrl());
		sb.append(getMainEndpoint()).append(CommonConstants.QUESTION_MARK);
		
		if (queryString != null && !queryString.isEmpty())
			sb.append(queryString).append(CommonConstants.AMPERSAND);

		if (sort != null && !sort.isEmpty())
			sb.append(sort).append(CommonConstants.AMPERSAND);

		if (limit > 0)
			sb.append("limit=").append(limit).append(CommonConstants.AMPERSAND);

		if (page > 0)
			sb.append("page=").append(page).append(CommonConstants.AMPERSAND);

		if (offset > 0)
			sb.append("offset=").append(offset).append(CommonConstants.AMPERSAND);
		
		try {			
		
			String json = RESTCommunication.getJsonPayload(sb.toString(), Manager.getAccessToken());
			System.out.println("Response JSON:\n" + json);
			return new Gson().fromJson(json, clazz);

		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Books due to - " + ioe.getMessage());
			return null;
		}

	}

	public abstract String getMainEndpoint();
	
	public static void main(String[] args) throws IOException {
		
		//System.out.println("Access Token: " + getAccessToken());
		//System.out.println("Base URL: " + getBaseUrl());

		System.out.println(RESTCommunication.getJsonPayload(getBaseUrl() + "/character", getAccessToken()));
		System.out.println(RESTCommunication.getJsonPayload(getBaseUrl() + "/character/5cd99d4bde30eff6ebccfbbe/quote", getAccessToken()));
		/*
		System.out.println("Books Size: " + new BookManager().getBooks().size());
		System.out.println("Chapters Size: " + new ChapterManager().getChapters().size());
		
		System.out.println("Book: " + new BookManager().getBook("5cf58077b53e011a64671583").getName());
		System.out.println("Chapter: " + new ChapterManager().getChapter("6091b6d6d58360f988133ba1").getChapterName());
		*/
		//System.out.println("Book Chapters: " + new BookManager().getBookChapters("5cf58077b53e011a64671583").size());

		//System.out.println("Quotes Size: " + new QuoteManager().getQuotes().size());
		//System.out.println("Quote: " + new QuoteManager().getQuote("5cd96e05de30eff6ebcce7e9").getDialog());

		//System.out.println("Characters Size: " + new CharacterManager().getCharacters().size());
		//System.out.println("Character: " + new CharacterManager().getCharacter("5cd99d4bde30eff6ebccfbbe").getName());
		//System.out.println("Character Quotes: " + new CharacterManager().getCharacterQuotes("5cd99d4bde30eff6ebccfbbe").size());

		//System.out.println("Movies Size: " + new MovieManager().getMovies().size());
		//System.out.println("Movie: " + new MovieManager().getMovie("5cd95395de30eff6ebccde56").getName());
		//System.out.println("Movies Quotes: " + new MovieManager().getMovieQuotes("5cd95395de30eff6ebccde56").size());

		
	}
	
}