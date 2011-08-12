package de.leuphana.ardrone.dronesystem.video;

import vi.VideoImage;
import vi.uint;

/**
 * Based on work by Daniel Schmidt and Wilke Jansoone. Reimplemented for Android
 * compatability.
 * 
 * @author Florian Quadt, Moritz Windelen
 * 
 */
public class DroneImageConverter {

	private static int width;
	private static int height;

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		DroneImageConverter.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		DroneImageConverter.height = height;
	}

	public static ImageData process(final byte[] rawData) {
		final VideoImage image = new VideoImage();
		image.AddImageStream(rawData);

		final uint[] outData = image.getPixelData();

		final byte[] outDataB = new byte[outData.length * 3];
		for (int i = 0; i < outData.length; i++) {
			final int i2 = i * 3;

			final uint dataI = outData[i];

			final byte[] elt = dataI.getBytes();
			outDataB[i2] = elt[2];
			outDataB[i2 + 1] = elt[1];
			outDataB[i2 + 2] = elt[0];

		}
		return new ImageData(outDataB, width, height);
	}
	/*
	 * final int[] pixelData = new int[76800];
	 * 
	 * int times = 100; for (int buff = 0; buff < times; buff++) { final byte[]
	 * processedData = process(rawData);
	 * 
	 * // System.out.println("file size:" + processedData.length);
	 * 
	 * int raw, pixel = 0, j = 0; for (int i = 0; i < pixelData.length; i++) {
	 * pixel = 0; raw = processedData[j++] & 0xFF; //
	 * System.out.println("raw[0]:" + Integer.toHexString(raw) + " " // + //
	 * " pixel " + Integer.toHexString(pixel)); pixel |= (raw << 16); raw =
	 * processedData[j++] & 0xFF; // System.out.println("raw[1]:" +
	 * Integer.toHexString(raw) + " " // + // " pixel " +
	 * Integer.toHexString(pixel)); pixel |= (raw << 8); raw =
	 * processedData[j++] & 0xFF; // System.out.println("raw[2]:" +
	 * Integer.toHexString(raw) + " " // + // " pixel " +
	 * Integer.toHexString(pixel)); pixel |= (raw << 0);
	 * 
	 * // System.out.println("pixel:" + Integer.toHexString(pixel));
	 * pixelData[i] = pixel; }
	 */
}
