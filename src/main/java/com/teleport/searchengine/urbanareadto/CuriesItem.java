package com.teleport.searchengine.urbanareadto;

import com.google.gson.annotations.SerializedName;

public class CuriesItem{

	@SerializedName("templated")
	private Boolean templated;

	@SerializedName("name")
	private String name;

	@SerializedName("href")
	private String href;
}