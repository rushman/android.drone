package de.leuphana.ardrone.dronesystem.video;

public class ImageData {
	// int imageColour = Config.RGB_565;//bisher Byte_grey
	// private final int[] pixelData;
	private final byte[] rawData;

	public byte[] getRawData() {
		return rawData;
	}

	int imageWidth = 320; // 320 hori ## 176 verti
	int imageHeight = 240;
	int offset = 0;
	int scansize = imageWidth;

	public ImageData(byte[] rawData, int imageWidth, int imageHeight) {
		super();
		this.rawData = rawData;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getOffset() {
		return offset;
	}

	public int getScansize() {
		return scansize;
	}

	public int[] colors_in_RGB565() {
		int[] pixelData = new int[rawData.length / 2];
		int raw, pixel, j = 0;
		for (int i = 0; i < pixelData.length; i++) {
			raw = pixelData[j++];
			pixel = ((raw & 0xf8) << 24) | ((raw & 0x07) << 13);
			raw = pixelData[j++];
			pixel |= ((raw & 0xe0) << 5) | ((raw & 0x1f) << 3);

			pixelData[i] = pixel;
		}
		return pixelData;
	}
}