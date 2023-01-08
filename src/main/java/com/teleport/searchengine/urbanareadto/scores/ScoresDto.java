package com.teleport.searchengine.urbanareadto.scores;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ScoresDto {

	@SerializedName("summary")
	private String summary;

	@SerializedName("_links")
	private Links links;

	@SerializedName("teleport_city_score")
	private Object teleportCityScore;

	@SerializedName("categories")
	private List<CategoriesItem> categories;
}