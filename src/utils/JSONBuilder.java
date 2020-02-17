package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JSONBuilder {
	private JsonObject object;
	
	public JSONBuilder() {
		this.object = new JsonObject();
	}
	
	public JSONBuilder add(String property, String value) {
		object.addProperty(property, value);
		return this;
	}

	public JSONBuilder add(String property, int value) {
		object.addProperty(property, value);
		return this;
	}

	public JSONBuilder add(String property, double value) {
		object.addProperty(property, value);
		return this;
	}

	public JSONBuilder add(String property, JsonArray list) {
		object.add(property, list.getAsJsonArray());
		return this;
	}

	public JsonObject create() {
		return object;
	}
}