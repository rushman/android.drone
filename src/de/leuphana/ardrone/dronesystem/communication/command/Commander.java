package de.leuphana.ardrone.dronesystem.communication.command;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import de.leuphana.ardrone.dronesystem.domain.CmdValue;
import de.leuphana.ardrone.dronesystem.domain.Command;
import de.leuphana.ardrone.dronesystem.network.CommandSender;

/**
 * Provides a ScheduledThreadPool to send the current command to the drone.
 * 
 * @author Florian, Moritz
 * 
 */
public class Commander {
	// while(true) 4 , e
	private static ScheduledExecutorService executorTassadar;
	private static Command commandInternal;
	private static ScheduledFuture<?> future;
	// 4 + e
	/**
	 * Runnable that is executed in by the ScheduledExecutorService and sends a
	 * command to the drone.
	 */
	private static Runnable probe = new Runnable() {

		@Override
		public void run() {
			CommandSender.INSTANCE.sendCommand(commandInternal
					.getMessageWithCounter());
		}
	};

	/**
	 * Starts the ThreadPool and attaches the Runnable. Attempts to shutdown the
	 * TreadPool if it already exists.
	 */
	public static void start() {
		if (executorTassadar != null) {
			executorTassadar.shutdownNow();
		}
		startInternal();
	}

	/**
	 * Stops and reopens the ThreadPool and the attached Runnable.
	 */
	public static void restart() {
		stop();
		startInternal();
	}

	/**
	 * Soft-reset: attempts to terminate the current Runnable, if unable to do
	 * so, falls back to {@link #kill()}
	 */
	public static void stop() {
		if (future != null) {
			boolean canceled = future.cancel(false);
			if (!canceled) {
				kill();
			}
		}
	}

	/**
	 * Hard-reset: Ignores any Runnables and attempts to kill the ThreadPool
	 * 
	 * @throws RuntimeException
	 *             if ThreadPool cannot be terminated.
	 */
	public static void kill() {
		if (executorTassadar != null) {
			executorTassadar.shutdownNow();
			if (!executorTassadar.isShutdown())
				throw new RuntimeException(
						"Unable to terminate tassadar, please contact your overmind");
		}
	}

	/**
	 * Sets the command to be sent to the drone. There is no warranty that a
	 * command is actually sent to the drone, as each call overwrites the
	 * previous value and the Runnable only reads the command every 30ms.
	 * 
	 * @param command
	 *            the command to be sent to the drone
	 */
	public synchronized static void setCommand(Command command) {
		commandInternal = command;
	}

	public synchronized static void sendInstantCommand(Command command) {
		CommandSender.INSTANCE.sendCommand(command.getMessageWithCounter());
	}

	public synchronized static void sendInstantCommand(String command) {
		CommandSender.INSTANCE.sendCommand(command);
	}

	/**
	 * Starts a new ScheduledThreadPool and attaches a Runnable to be executed
	 * every 300ms
	 */
	private static void startInternal() {
		executorTassadar = Executors.newScheduledThreadPool(1);
		future = executorTassadar.scheduleAtFixedRate(probe, 0, 300,
				TimeUnit.MILLISECONDS);
	}

	public static void setCommand(CmdValue commandValue) {
		setCommand(new Command(commandValue));
	}

}
