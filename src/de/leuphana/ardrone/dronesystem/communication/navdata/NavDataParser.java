package de.leuphana.ardrone.dronesystem.communication.navdata;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import de.leuphana.ardrone.dronesystem.api.INavDataOption;
import de.leuphana.ardrone.dronesystem.domain.DemoNavData;
import de.leuphana.ardrone.dronesystem.domain.DroneDataContainer;

public class NavDataParser {
	/*
	 * navigation bytes listings from
	 * http://code.google.com/p/ardroneme/source/checkout (cause missing in the
	 * ardrone documentation)
	 */
	// static final int NAV_STATE_OFFSET = 4;
	// static final int NAV_BATTERY_OFFSET = 24;
	// static final int NAV_PITCH_OFFSET = 28;
	// static final int NAV_ROLL_OFFSET = 32;
	// static final int NAV_YAW_OFFSET = 36;
	// static final int NAV_ALTITUDE_OFFSET = 40;

	/**
	 * @param navData
	 */
	private NavDataParser() {
	}

	public static DroneDataContainer parse(byte[] bytes) {
		DroneDataContainer container = new DroneDataContainer();
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		// 0-3
		int header = buffer.getInt();
		if (header != 0x55667788) {
			return null;
		}
		// 4-7
		container.setStateValues(buffer.getInt());
		// System.out.println("STATE:" + navData.stateValues);

		// 8-11
		container.setSeqNumber(buffer.getInt());
		// System.out.println("seqNumber:" + navData.seqNumber);
		// 12-15
		container.setVisionFlag(buffer.getInt());
		while (buffer.position() < buffer.limit() - 64) {
			// get the tag. length 16 bit. bitmask to ensure correct
			// typeconversion from short to int
			int tag = buffer.getShort() & 0xFFFF;
			// get the blocksize (complete size including tag and size).
			// length: 16 bit)
			// size is number of bytes. optionSize is therefore 4 bytes
			// smaller
			int gesamt = (buffer.getShort() & 0xFFFF);
			int optionsSize = gesamt - 4;
			if (optionsSize > 0) {
				byte[] dst = new byte[optionsSize];
				buffer.get(dst, 0, optionsSize);
				ByteBuffer option = ByteBuffer.wrap(dst);
				option.order(ByteOrder.LITTLE_ENDIAN);
				buffer.position(buffer.position() + optionsSize);
				container.add(handleOption(tag, option));
			}
		}
		// TODO int checksum last 64 bit

		return container;
	}

	private static INavDataOption handleOption(int tag, ByteBuffer option) {
		try {
			DemoNavData demoNavData = new DemoNavData();
			demoNavData.ctrlState = option.getInt();
			// System.out.println(this.navData.stateValues);
			System.out.println(demoNavData.ctrlState);
			demoNavData.setBatteryLevel(option.getInt());
			demoNavData.pitch = option.getFloat();
			// demoNavData.rotate = option.getFloat();
			// demoNavData.yaw = option.getFloat() ;
			demoNavData.yaw = option.getFloat();
			demoNavData.rotate = option.getFloat();
			demoNavData.altitude = option.getInt();
			demoNavData.vx = option.getFloat();
			demoNavData.vy = option.getFloat();
			demoNavData.vz = option.getFloat();
			demoNavData.frameIndex = option.getInt();
			return (INavDataOption)demoNavData;

			// ignore errors. NavData has to handle connection problems or
			// invalid data
		} catch (BufferUnderflowException e) {
			System.out.println("Buffer underflow");
			return null;
		}

	}

}