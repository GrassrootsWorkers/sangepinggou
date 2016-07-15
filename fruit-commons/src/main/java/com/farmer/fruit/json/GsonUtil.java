package com.farmer.fruit.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	
public static Gson JSON;
	
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateTimeSerializer());
		gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateTimeDeserializer());
		
		JSON = gsonBuilder.create();
	}
	
	public static String getJSON(Object o) {
		return JSON.toJson(o);
	}
	
	public static <E> E getObject(String json, Class<E> t) {
		return (E) JSON.fromJson(json, t);
	}
	
}
