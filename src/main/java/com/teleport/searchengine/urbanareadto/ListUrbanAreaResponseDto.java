package com.teleport.searchengine.urbanareadto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ListUrbanAreaResponseDto {

	@SerializedName("_links")
	private Links links;

	@SerializedName("count")
	private Integer count;
}