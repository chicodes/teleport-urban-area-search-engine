package com.teleport.searchengine.urbanareadto.salaries;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SalaryPercentiles{

	@SerializedName("percentile_50")
	private Object percentile50;

	@SerializedName("percentile_75")
	private Object percentile75;

	@SerializedName("percentile_25")
	private Object percentile25;
}