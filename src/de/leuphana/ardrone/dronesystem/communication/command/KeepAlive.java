package de.leuphana.ardrone.dronesystem.communication.command;

import static de.leuphana.ardrone.dronesystem.domain.CmdValue.COMMAND_RESETWATCHDOG;

import java.util.Timer;
import java.util.TimerTask;

import de.leuphana.ardrone.dronesystem.network.CommandSender;

/**
 * Sends the resetwatchdog command every 30ms to ensure that the drone never
 * goes into lock-down.
 * 
 * @author Florian, Moritz
 * 
 */
public class KeepAlive {
	private static Timer t;

	private KeepAlive() {
	}

	/**
	 * Starts the Timer controlling the keep-alive signal.
	 */
	public static void start() {
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {

				CommandSender.INSTANCE.sendCommand(COMMAND_RESETWATCHDOG
						.asCommand());
			}
		}, 0 /* initial delay */, 30 /* period */);
	}

	/**
	 * Stops the timer.
	 */
	public static void stop() {
		if (t != null)
			t.cancel();
	}
}
