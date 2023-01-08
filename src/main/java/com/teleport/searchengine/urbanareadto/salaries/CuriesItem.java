package com.teleport.searchengine.urbanareadto.salaries;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CuriesItem{

	@SerializedName("templated")
	private Boolean templated;

	@SerializedName("name")
	private String name;

	@SerializedName("href")
	private String href;
}