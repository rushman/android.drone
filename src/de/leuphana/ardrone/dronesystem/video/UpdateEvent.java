package de.leuphana.ardrone.dronesystem.video;

import java.util.EventObject;

public class UpdateEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8315985652406100741L;
	ImageData data;

	public UpdateEvent(Object source, ImageData data) {
		super(source);
		this.data = data;
	}

	public ImageData getData() {
		return data;
	}

	public void setData(ImageData data) {
		this.data = data;
	}

}
