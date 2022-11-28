package com.liblab.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

	@SerializedName("_id")
    @Expose
	private String id;

	@SerializedName("name")
    @Expose
	private String name;

	@SerializedName("runtimeInMinutes")
    @Expose
	private Integer runtimeInMinutes;

	@SerializedName("budgetInMillions")
    @Expose
	private Integer budgetInMillions;

	@SerializedName("boxOfficeRevenueInMillions")
    @Expose
	private Float boxOfficeRevenueInMillions;

	@SerializedName("academyAwardNominations")
    @Expose
	private Integer academyAwardNominations;

	@SerializedName("academyAwardWins")
    @Expose
	private Integer academyAwardWins;

	@SerializedName("rottenTomatoesScore")
    @Expose
	private Float rottenTomatoesScore;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getRuntimeInMinutes() {
		return runtimeInMinutes;
	}

	public void setRuntimeInMinutes(Integer runtimeInMinutes) {
		this.runtimeInMinutes = runtimeInMinutes;
	}

	public Integer getBudgetInMillions() {
		return budgetInMillions;
	}

	public void setBudgetInMillions(Integer budgetInMillions) {
		this.budgetInMillions = budgetInMillions;
	}

	public Float getBoxOfficeRevenueInMillions() {
		return boxOfficeRevenueInMillions;
	}

	public void setBoxOfficeRevenueInMillions(Float boxOfficeRevenueInMillions) {
		this.boxOfficeRevenueInMillions = boxOfficeRevenueInMillions;
	}

	public Integer getAcademyAwardNominations() {
		return academyAwardNominations;
	}

	public void setAcademyAwardNominations(Integer academyAwardNominations) {
		this.academyAwardNominations = academyAwardNominations;
	}

	public Integer getAcademyAwardWins() {
		return academyAwardWins;
	}

	public void setAcademyAwardWins(Integer academyAwardWins) {
		this.academyAwardWins = academyAwardWins;
	}

	public Float getRottenTomatoesScore() {
		return rottenTomatoesScore;
	}

	public void setRottenTomatoesScore(Float rottenTomatoesScore) {
		this.rottenTomatoesScore = rottenTomatoesScore;
	}
		
}