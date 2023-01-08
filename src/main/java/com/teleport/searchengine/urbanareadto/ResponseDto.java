package com.teleport.searchengine.urbanareadto;

import com.google.gson.annotations.SerializedName;

public class ResponseDto{

	@SerializedName("continent")
	private String continent;

	@SerializedName("ua_id")
	private String uaId;

	@SerializedName("teleport_city_url")
	private String teleportCityUrl;

	@SerializedName("full_name")
	private String fullName;

	@SerializedName("is_government_partner")
	private Boolean isGovernmentPartner;

	@SerializedName("bounding_box")
	private BoundingBox boundingBox;

	@SerializedName("_links")
	private Links2 links2;

	@SerializedName("name")
	private String name;

	@SerializedName("slug")
	private String slug;
}