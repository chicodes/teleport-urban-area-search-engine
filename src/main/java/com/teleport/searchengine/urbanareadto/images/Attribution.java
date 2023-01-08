package com.teleport.searchengine.urbanareadto.images;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Attribution{

	@SerializedName("license")
	private String license;

	@SerializedName("site")
	private String site;

	@SerializedName("photographer")
	private String photographer;

	@SerializedName("source")
	private String source;
}