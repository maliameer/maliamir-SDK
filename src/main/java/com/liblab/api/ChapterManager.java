package com.liblab.api;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;

import retrofit2.Call;

import com.liblab.rest.RESTCommunication;
import com.liblab.rest.RESTServices;

import com.liblab.api.model.Chapter;
import com.liblab.api.model.Chapters;

public class ChapterManager extends Manager {

	private static Logger logger = Logger.getLogger(ChapterManager.class.getName());

	public String getMainEndpoint() {
		return RESTServices.CHAPTER;
	}

	/**
	 * Search List of Chapters as per provided parameters.
	 * @param queryString: Query String to hold param-value pair like name=Rings. It can be multiple pairs separate by '&amp;'
	 * @param sort: Like name:asc; id:desc etc.
	 * @param limit to limit number of records.
	 * @param page specific page.
	 * @param offset Record's offset.
	 * @return List of Chapter instances matching to the provided parameters.
	 */
	public List<Chapter> getChapters(String queryString, String sort, int limit, int page, int offset) {
		Chapters chapters = (Chapters)super.get(Chapters.class, queryString, sort, limit, page, offset);
		return chapters.getChapters();
	}

	public List<Chapter> getChapters() {
		
		Call<Chapters> chaptersRestCall = RESTCommunication.getRESTServicesInstance().getChapters(Manager.getBearerAuthorization());
		try {
			return chaptersRestCall.execute().body().getChapters();
		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Chapters due to - " + ioe.getMessage());
			return null;
		}

	}

	public Chapter getChapter(String id) {
		
		Call<Chapters> chaptersRestCall = RESTCommunication.getRESTServicesInstance().getChapter(Manager.getBearerAuthorization(), id);
		try {
			List<Chapter> chapters = chaptersRestCall.execute().body().getChapters();
			return chapters != null && chapters.size() > 0 ? chapters.get(0) : null;
		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Chapter due to - " + ioe.getMessage());
			return null;
		}
		
	}
}