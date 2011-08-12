package de.leuphana.ardrone.dronesystem.communication.command;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.leuphana.ardrone.dronesystem.domain.CmdValue;
import de.leuphana.ardrone.dronesystem.domain.util.Counter;
import de.leuphana.ardrone.dronesystem.network.CommandSender;
import de.leuphana.ardrone.dronesystem.network.VideoReceiver;
import de.leuphana.ardrone.dronesystem.video.DroneImageConverter;
import de.leuphana.ardrone.dronesystem.video.ImageData;
import de.leuphana.ardrone.dronesystem.video.ImageUpdateListener;
import de.leuphana.ardrone.dronesystem.video.UpdateEvent;

public class VideoStreamer {
	private static Timer t;
	private static boolean initialized = false;
	private static List<ImageUpdateListener> listeners;

	private VideoStreamer() {
	}

	/**
	 * Starts the Timer controlling the navdata receiver.
	 */
	public static void start() {
		if (!initialized) {
			init();
		}
		VideoReceiver.INSTANCE.open();
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				byte[] bytes;
				try {

					bytes = VideoReceiver.INSTANCE.receive();
					ImageData imageData = DroneImageConverter.process(bytes);
					fireEvent(imageData);

					VideoReceiver.INSTANCE.sendTrash();

				} catch (Exception e) {
					System.out.println("timeOut");
					// e.printStackTrace();
				}
			}
		}, 0 /* initial delay */, 30 /* period */);
	}

	private static void init() {
		System.out.println("init started");
		try {
			// System.out.println("Send trigger flag to UDP port ");
			VideoReceiver.INSTANCE.sendTrash();
			// Image site setzen
			DroneImageConverter.setWidth(320);// eventually different
												// resolution
			DroneImageConverter.setHeight(240);
			// Video Channel setzen
			CommandSender.INSTANCE.sendCommand(CmdValue.CONF_VIDEO.with(Counter
					.get()));
			CommandSender.INSTANCE.sendCommand(CmdValue.COMMAND_ACK
					.with(Counter.get()));

		} catch (IOException e) {
			System.err.println("Unable to init videostreamer");
			// e.printStackTrace();
		}

	}

	/**
	 * Stops the timer.
	 */
	public static void stop() {
		VideoReceiver.INSTANCE.close();
		t.cancel();
	}

	public static void fireEvent(ImageData data) {
		for (ImageUpdateListener listener : listeners) {
			listener.onUpdate(new UpdateEvent(VideoStreamer.class, data));
		}
	}

	public static void addListener(ImageUpdateListener listener) {
		listeners.add(listener);
	}

	public static void removeListener(ImageUpdateListener listener) {
		listeners.remove(listener);
	}
}
