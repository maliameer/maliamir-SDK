package com.liblab.api.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chapters {

	@SerializedName("docs")
	@Expose
	private List<Chapter> chapters;

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

}