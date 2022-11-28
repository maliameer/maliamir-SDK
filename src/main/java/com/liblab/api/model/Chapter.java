package com.liblab.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chapter {

	@SerializedName("_id")
    @Expose
	private String id;

	@SerializedName("chapterName")
    @Expose
	private String chapterName;

	@SerializedName("book")
    @Expose
	private String bookId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getChapterName() {
		return chapterName;
	}
	
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
}