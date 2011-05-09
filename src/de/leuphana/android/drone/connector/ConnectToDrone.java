package de.leuphana.android.drone.connector;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ConnectToDrone {

	private InetAddress inet_addr;
	private DatagramSocket socket;

	public ConnectToDrone(String ip) {

		String intern_Ip;

		if (ip == null) {

			intern_Ip = "192.168.1.1";

		} else {

			intern_Ip = ip;

		}

		try {

			// needed for DatagramSocket
			this.inet_addr = InetAddress.getByName(intern_Ip);

			// establishing UDPConnection
			this.socket = new DatagramSocket();
			this.socket.setSoTimeout(3000);

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (SocketException e) {

			e.printStackTrace();
		}

	}

	// finish command with r
	public boolean sendCommand(String command) {

		return this.send(command + "\r");

	}

	private boolean send(String command) {

		// command as bytes
		byte[] commandAsBytes = command.getBytes();
		System.out.println("Achtung" + command);

		// create the packet
		DatagramPacket packet = new DatagramPacket(commandAsBytes, commandAsBytes.length,
			this.inet_addr, 5556);

		try {

			// send packet to drone
			this.socket.send(packet);

			sleep(1);

			return true;

		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}

	}

	// sleep method between commands
	private static void sleep(int seconds) {

		try {

			Thread.sleep(seconds * 1000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

}
