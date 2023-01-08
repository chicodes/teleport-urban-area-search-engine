package com.teleport.searchengine.urbanareadto.images;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Image{

	@SerializedName("web")
	private String web;

	@SerializedName("mobile")
	private String mobile;
}