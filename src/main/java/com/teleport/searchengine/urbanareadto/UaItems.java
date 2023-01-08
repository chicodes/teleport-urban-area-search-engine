package com.teleport.searchengine.urbanareadto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class UaItems {

	@SerializedName("name")
	private String name;

	@SerializedName("href")
	private String href;
}