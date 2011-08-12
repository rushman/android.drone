package de.leuphana.ardrone.dronesystem.domain.util;

/**
 * Provides default values and convenience methods.
 * 
 * @author Florian, Moritz
 * 
 */
public class Config {

	private static String ip = "192.168.1.1";
	private static int cmdPort = 5556;
	private static int navDataPort = 5554;
	private static int videoPort = 5555;

	public static int getVideoPort() {
		return videoPort;
	}

	/**
	 * Provides the IP-Address that all sockets should connect to.
	 * 
	 * @return the IP-Address
	 */
	public static String getIp() {
		return ip;
	}

	/**
	 * Provides the Port that the socket responsible for sending commands should
	 * attach to.
	 * 
	 * @return the port
	 */
	public static int getCmdPort() {
		return cmdPort;
	}

	/**
	 * Provides the Port that the socket responsible for receiving navData
	 * should attach to.
	 * 
	 * @return the port
	 */
	public static int getNavDataPort() {
		return navDataPort;
	}

}
