package com.google.appengine.api.images;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

public class ImagesService {
	public enum OutputEncoding {
		JPEG, 
		PNG, 
		WEBP; 
	}

	public Image applyTransform(Transform transform, Image image, OutputEncoding outputEncoding) {
		return applyTransform(transform, image, new OutputSettings(outputEncoding));
	}

	public Image applyTransform(Transform transform, Image image, OutputSettings settings) {
		switch (transform.getType()) {
		case CROP:
			return this.crop(image, (int)transform.get("left"), (int)transform.get("top"), (int)transform.get("right"), (int)transform.get("bottom"));
		case ROTATE:
			return this.rotate(image, (int)transform.get("rotation"));
		case RESIZE:
			return this.resize(image, (int)transform.get("width"), (int)transform.get("height"));
		default:
			throw new UnsupportedOperationException("uknown transform type");
		}
		
	}
	
	private Image resize(Image image, int width, int height) {
		Mode translationMode = Mode.AUTOMATIC;
		if (image.getWidth() < width && image.getHeight() < height) {
			return image;
		} else if (image.getWidth() < width) {
			translationMode = Mode.FIT_TO_HEIGHT;
		} else if (image.getHeight() < height) {
			translationMode = Mode.FIT_TO_WIDTH;
		} else {
			float wRatio = ((float)width / (float)image.getWidth());
			float hRatio = ((float)height / (float)image.getHeight());
			translationMode = wRatio < hRatio ? Mode.FIT_TO_WIDTH : Mode.FIT_TO_HEIGHT;
		}
				
		BufferedImage bufferedImage = Scalr.resize(image.getNativeImage(), translationMode, width, height, (BufferedImageOp)null);
		
		/*
		java.awt.Image scaledImage = image.getNativeImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		BufferedImage bufferedImage = new BufferedImage(width, height, image.getNativeImage().getType());
		bufferedImage.getGraphics().drawImage(scaledImage, 0, 0 , null);
		*/
		return new Image(bufferedImage, image.getFormat());
	}
	
	private Image crop(Image image, int left, int top, int right, int bottom) {
		BufferedImage croppedImage = image.getNativeImage().getSubimage(left, top, right, bottom);
		return new Image(croppedImage, image.getFormat());
	}
	
	private Image rotate(Image image, int rotation) {
		BufferedImage rotatedImage = new BufferedImage((int)image.getHeight(), (int)image.getWidth(), image.getType());
		Graphics2D graphics = null;
		try {

			graphics = (Graphics2D) rotatedImage.getGraphics();
			graphics.rotate(Math.toRadians(rotation));
			graphics.drawImage(
					image.getNativeImage(), 
					rotation < 0 ? -rotatedImage.getHeight() : 0, 
					rotation > 0 ? -rotatedImage.getWidth() : 0, 
							null);
			
			return new Image(rotatedImage, image.getFormat());
		} finally {
			if (null != graphics) {
				graphics.dispose();
			}
		}
	}
}
