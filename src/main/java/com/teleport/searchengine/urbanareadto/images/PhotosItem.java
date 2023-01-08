package com.teleport.searchengine.urbanareadto.images;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PhotosItem{

	@SerializedName("image")
	private Image image;

	@SerializedName("attribution")
	private Attribution attribution;
}