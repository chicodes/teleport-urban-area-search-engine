package com.teleport.searchengine.urbanareadto.scores;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CategoriesItem{

	@SerializedName("color")
	private String color;

	@SerializedName("score_out_of_10")
	private Object scoreOutOf10;

	@SerializedName("name")
	private String name;
}