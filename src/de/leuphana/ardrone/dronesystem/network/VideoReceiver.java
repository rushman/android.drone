package de.leuphana.ardrone.dronesystem.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import de.leuphana.ardrone.dronesystem.domain.util.Config;

public enum VideoReceiver {
	INSTANCE;
	private DatagramSocket videoSocket;
	private InetAddress address;

	private VideoReceiver() {

	}

	public void open() {
		try {
			this.address = InetAddress.getByName(Config.getIp());
			videoSocket = new DatagramSocket(Config.getVideoPort());
			videoSocket.setSoTimeout(3000);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public byte[] receive() {
		// TODO buffer size is 320*240*2
		byte[] buffer = new byte[153600];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
				address, Config.getVideoPort());
		try {
			videoSocket.receive(packet);
			return packet.getData();
		} catch (IOException e) {
			// TODO e.printStackTrace();
			return new byte[] { 0 };
		}
	}

	public void sendTrash() throws IOException {
		byte[] buffer = { 0x1, 0x0, 0x0, 0x0 };
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
				address, Config.getVideoPort());
		try {
			videoSocket.send(packet);

		} catch (IOException e) {
			System.out.println("init sequence failed!");
			close();
			throw e;
		}
	}

	public boolean isOpen() {
		return videoSocket.isConnected();
	}

	public void close() {
		videoSocket.close();
	}
}