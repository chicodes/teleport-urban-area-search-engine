package com.teleport.searchengine.urbanareadto;

import com.google.gson.annotations.SerializedName;

public class Latlon{

	@SerializedName("east")
	private Object east;

	@SerializedName("south")
	private Object south;

	@SerializedName("north")
	private Object north;

	@SerializedName("west")
	private Object west;
}