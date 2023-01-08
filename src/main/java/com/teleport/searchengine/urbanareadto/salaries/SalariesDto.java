package com.teleport.searchengine.urbanareadto.salaries;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SalariesDto {

	@SerializedName("salaries")
	private List<SalariesItem> salaries;

	@SerializedName("_links")
	private Links links;
}