package com.google.appengine.api.images;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class Image {
	private BufferedImage image;
	private Format format = Format.JPEG;
	public enum Format {
		BMP, 
		GIF, 
		ICO, 
		JPEG, 
		PNG, 
		TIFF, 
		WEBP; 		
	}
	
	public Image(byte[] bytes) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ImageInputStream iis = ImageIO.createImageInputStream(bais);

		InputStream in = new ByteArrayInputStream(bytes);
		this.image = ImageIO.read(in);
		
		Iterator<ImageReader> itr = ImageIO.getImageReaders(iis);
		if (!itr.hasNext()) {
			throw new IOException("no readers for image type");
		}
		ImageReader reader = itr.next();
	    this.format = Format.valueOf(reader.getFormatName().toUpperCase());
	    if (null == this.format) {
	    	this.format = Format.JPEG;
	    }
	}

	public Image(BufferedImage image, Format format) {
		this.image = image;
		this.format = format;
	}

	public long getHeight() {
		return this.image.getHeight();
	}

	public long getWidth() {
		return this.image.getWidth();
	}
	
	public Format getFormat() {
		return this.format;
	}
	
	public int getType() {
		return this.image.getType();
	}

	public BufferedImage getNativeImage() {
		return this.image;
	}
	
	public byte[] getImageData() throws InvalidObjectException {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(this.image, this.format.name(), baos);
			baos.flush();
			byte[] bytes = baos.toByteArray();
			baos.close();
			return bytes;
		} catch(IOException e) {
			throw new InvalidObjectException(e.toString());
		}
	}
}
