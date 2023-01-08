package com.teleport.searchengine.urbanareadto;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Links2 {

	@SerializedName("ua:salaries")
	private UaSalaries uaSalaries;

	@SerializedName("curies")
	private List<CuriesItem> curies;

	@SerializedName("ua:identifying-city")
	private UaIdentifyingCity uaIdentifyingCity;

	@SerializedName("ua:continent")
	private UaContinent uaContinent;

	@SerializedName("ua:cities")
	private UaCities uaCities;

	@SerializedName("self")
	private Self self;

	@SerializedName("ua:scores")
	private UaScores uaScores;

	@SerializedName("ua:images")
	private UaImages uaImages;

	@SerializedName("ua:admin1-divisions")
	private List<UaAdmin1DivisionsItem> uaAdmin1Divisions;

	@SerializedName("ua:countries")
	private List<UaCountriesItem> uaCountries;

	@SerializedName("ua:details")
	private UaDetails uaDetails;

	@SerializedName("ua:primary-cities")
	private List<UaPrimaryCitiesItem> uaPrimaryCities;
}