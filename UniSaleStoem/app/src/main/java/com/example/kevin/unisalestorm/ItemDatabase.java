package com.example.kevin.unisalestorm;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/*
 * An extension of ParseObject that makes
 * it more convenient to access information
 * about a given Meal 
 */

@ParseClassName("ItemDatabase")
public class ItemDatabase extends ParseObject {

	public ItemDatabase() {
		// A default constructor is required.
	}

	public ParseFile getPhotoFile() {
		return getParseFile("photo");
	}

	public void setPhotoFile(ParseFile file) {
		put("photo", file);
	}

}
