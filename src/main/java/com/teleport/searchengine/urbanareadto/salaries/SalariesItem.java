package com.teleport.searchengine.urbanareadto.salaries;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SalariesItem{

	@SerializedName("salary_percentiles")
	private SalaryPercentiles salaryPercentiles;

	@SerializedName("job")
	private Job job;
}