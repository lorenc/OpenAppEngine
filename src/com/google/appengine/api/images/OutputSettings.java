package com.google.appengine.api.images;

import com.google.appengine.api.images.ImagesService.OutputEncoding;

public class OutputSettings {

	private OutputEncoding outputEncoding = OutputEncoding.JPEG;
	private int quality = -1;
	
	public OutputSettings(OutputEncoding encoding) {
		this.outputEncoding = encoding;
	}

	public void setQuality(int value) {
		this.quality = value;
	}

	public OutputEncoding getEncoding() {
		return this.outputEncoding;
	}
	
	public int getQuality() {
		return this.quality;
	}
}
