package com.google.appengine.api.images;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ImagesServiceFactory {
	private static String LOG_TAG = ImagesServiceFactory.class.getName();
	public static Image makeImage(byte[] bytes) {
		try {
			return new Image(bytes);
		} catch(Exception e) {
			Logger.getLogger(LOG_TAG).log(Level.SEVERE, e.toString());
			return null;
		}
	}

	public static ImagesService getImagesService() {
		return new ImagesService();
	}

	public static Transform makeCrop(int left, int top, int right, int bottom) {
		return new Transform(left, top, right, bottom);
	}

	public static Transform makeResize(int width, int height) {
		return new Transform(width, height);
	}

	public static Transform makeRotate(int rotation) {
		return new Transform(rotation);
	}

}
