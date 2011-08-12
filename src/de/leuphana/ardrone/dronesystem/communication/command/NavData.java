package de.leuphana.ardrone.dronesystem.communication.command;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import de.leuphana.ardrone.dronesystem.communication.navdata.ArDroneState;
import de.leuphana.ardrone.dronesystem.communication.navdata.ArDroneState.Mask;
import de.leuphana.ardrone.dronesystem.communication.navdata.NavDataParser;
import de.leuphana.ardrone.dronesystem.domain.CmdValue;
import de.leuphana.ardrone.dronesystem.domain.DroneDataContainer;
import de.leuphana.ardrone.dronesystem.domain.util.Counter;
import de.leuphana.ardrone.dronesystem.network.NavDataReceiver;

public class NavData {

	private static Timer t;
	private static boolean initialized = false;

	private NavData() {
	}

	/**
	 * Starts the Timer controlling the navdata receiver.
	 */
	public static void start() {
		if (!initialized) {
			init();
		}
		NavDataReceiver.INSTANCE.open();
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				byte[] bytes;
				try {
					bytes = NavDataReceiver.INSTANCE.receive();
					DroneDataContainer dataContainer = NavDataParser.parse(bytes);
				} catch (Exception e) {
					System.out.println("timeOut");
					// e.printStackTrace();
				}
			}
		}, 0 /* initial delay */, 30 /* period */);
	}

	private static DroneDataContainer init() {
		System.out.println("init started");
		try {
			NavDataReceiver.INSTANCE.open();
			NavDataReceiver.INSTANCE.sendTrash();
			byte[] data = NavDataReceiver.INSTANCE.receive();
			DroneDataContainer dataContainer = NavDataParser.parse(data);
			if (ArDroneState.isOne(Mask.NAVDATA_BOOTSTRAP, dataContainer.getStateValues())) {
				System.out.println("Bootstrap active.");
				System.out.println("Changing to demo...");
				Commander.sendInstantCommand("AT*CONFIG=\"general:navdata_demo\",\"TRUE\"");
				Commander.sendInstantCommand(CmdValue.CONF_NAVDATA_TRUE.with(Counter.get()));
				data = NavDataReceiver.INSTANCE.receive();
				dataContainer = NavDataParser.parse(data);
				if (ArDroneState.isZero(Mask.COMMAND_MASK, dataContainer.getStateValues())) {
					System.out.println("Command activated");
					System.out.println("Running in demo mode");
					Commander.sendInstantCommand("AT*CTRL=0");
					return dataContainer;
				}
				// DroneConnect.instance.sendCommand("AT*CTRL=0");
				// DroneConnect.instance.sendCommand(String.format(Commands.CTRL.getCommandString(),CommandCounter.getCounter()));
				// TODO System.err.println("Unable to init NAVDATA");
			}
			return null;
		} catch (IOException e) {
			System.err.println("Unable to init NAVDATA");
			// e.printStackTrace();
			return null;
		}

	}

	/**
	 * Stops the timer.
	 */
	public static void stop() {
		NavDataReceiver.INSTANCE.close();
		t.cancel();
	}

}
