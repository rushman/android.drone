package de.leuphana.ardrone.dronesystem.management;

import de.leuphana.ardrone.dronesystem.communication.command.VideoStreamer;
import de.leuphana.ardrone.dronesystem.video.ImageUpdateListener;

public class VideoManager {

	public VideoManager() {

	}

	public void addListener(ImageUpdateListener listener) {
		VideoStreamer.addListener(listener);
	}

	public void removeListener(ImageUpdateListener listener) {
		VideoStreamer.removeListener(listener);
	}

	public void start() {
		VideoStreamer.start();
	}

	public void stop() {
		VideoStreamer.stop();
	}

}
