package com.teleport.searchengine.urbanareadto.salaries;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Job{

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;
}