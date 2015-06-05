package com.google.appengine.api.images;

import java.util.HashMap;
import java.util.Map;

public class Transform {
	private Type type;
	private Map<String, Object> properties = new HashMap<String, Object>();
	public enum Type {
		CROP,
		ROTATE,
		RESIZE
	};
	
	public Transform(int left, int top, int right, int bottom) {
		this.type = Type.CROP;
		this.properties.put("left", Integer.valueOf(left));
		this.properties.put("top", Integer.valueOf(top));
		this.properties.put("right", Integer.valueOf(right));
		this.properties.put("bottom", Integer.valueOf(bottom));
	}

	public Transform(int width, int height) {
		this.type = Type.RESIZE;
		this.properties.put("width", Integer.valueOf(width));
		this.properties.put("height", Integer.valueOf(height));
	}

	public Transform(int rotation) {
		this.type = Type.ROTATE;
		this.properties.put("rotation", Integer.valueOf(rotation));
	}

	public Type getType() {
		return this.type;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T)this.properties.get(name);
	}
}
