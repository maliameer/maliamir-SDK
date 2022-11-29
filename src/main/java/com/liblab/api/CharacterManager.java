package com.liblab.api;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;

import com.google.gson.Gson;

import retrofit2.Call;

import com.liblab.common.CommonConstants;

import com.liblab.rest.RESTCommunication;
import com.liblab.rest.RESTServices;

import com.liblab.api.model.Character;
import com.liblab.api.model.Characters;
import com.liblab.api.model.Quote;
import com.liblab.api.model.Quotes;

public class CharacterManager extends Manager {

	private static Logger logger = Logger.getLogger(CharacterManager.class.getName());

	public String getMainEndpoint() {
		return RESTServices.CHARACTER;
	}

	/**
	 * Search List of Character as per provided parameters.
	 * @param queryString: Query String to hold param-value pair like name=Rings. It can be multiple pairs separate by '&amp;'
	 * @param sort: Like name:asc; id:desc etc.
	 * @param limit to limit number of records.
	 * @param page specific page.
	 * @param offset Record's offset.
	 * @return List of Character instances matching to the provided parameters.
	 */
	public List<Character> getCharacters(String queryString, String sort, int limit, int page, int offset) {
		Characters characters = (Characters)super.get(Characters.class, queryString, sort, limit, page, offset);
		return characters.getCharacters();
	}

	public List<Character> getCharacters() {
		
		Call<Characters> charactersRestCall = RESTCommunication.getRESTServicesInstance().getCharacters(Manager.getBearerAuthorization());
		try {
			return charactersRestCall.execute().body().getCharacters();
		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Characters due to - " + ioe.getMessage());
			return null;
		}

	}

	public Character getCharacter(String id) {
		
		Call<Characters> charactersRestCall = RESTCommunication.getRESTServicesInstance().getCharacter(Manager.getBearerAuthorization(), id);
		try {
			List<Character> characters = charactersRestCall.execute().body().getCharacters();
			return characters != null && characters.size() > 0 ? characters.get(0) : null;
		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Character due to - " + ioe.getMessage());
			return null;
		}
		
	}
	
	public List<Quote> getCharacterQuotes(String id) {

		StringBuilder sb = new StringBuilder(Manager.getBaseUrl());
		sb.append(getMainEndpoint()).append(CommonConstants.URL_DELIMITER);
		sb.append(id).append(CommonConstants.URL_DELIMITER).append(RESTServices.QUOTE);
		
		try {			
			
			String json = RESTCommunication.getJsonPayload(sb.toString(), Manager.getAccessToken());
			return ((Quotes)new Gson().fromJson(json, Quotes.class)).getQuotes();

		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Books due to - " + ioe.getMessage());
			return null;
		}
		
	}

}