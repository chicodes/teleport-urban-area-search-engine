package com.teleport.searchengine.urbanareadto;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Links {

	@SerializedName("curies")
	private List<CuriesItem> curies;

	@SerializedName("self")
	private Self self;

	@SerializedName("ua:items")
	private List<UaItems> uaItems;
}