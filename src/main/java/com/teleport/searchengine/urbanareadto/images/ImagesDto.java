package com.teleport.searchengine.urbanareadto.images;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ImagesDto {

	@SerializedName("_links")
	private Links links;

	@SerializedName("photos")
	private List<PhotosItem> photos;
}