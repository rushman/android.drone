package de.leuphana.ardrone.dronesystem.communication.navdata;

public class ArDroneState {

	public enum Mask {
		/*
		 * !< FLY MASK : (0) ardrone is landed, (1) ardrone is flying
		 */
		FLY(1 << 0),
		/*
		 * !< VIDEO MASK : (0) video disable, (1) video enable
		 */
		VIDEO(1 << 1),
		/*
		 * !< VISION MASK : (0) vision disable, (1) vision enable
		 */
		VISION_MASK(1 << 2),
		/*
		 * !< CONTROL ALGO : (0) euler angles control, (1) angular speed control
		 */
		CONTROL_MASK(1 << 3),
		/*
		 * !< ALTITUDE CONTROL ALGO : (0) altitude control inactive (1) altitude
		 * control active
		 */
		ALTITUDE_MASK(1 << 4),
		/* !< USER feedback : Start button state */
		USER_FEEDBACK_START(1 << 5),
		/*
		 * !< Control command ACK : (0) None, (1) one received
		 */
		COMMAND_MASK(1 << 6),
		/* Firmware file is good (1) */
		FW_FILE_MASK(1 << 7),
		/* Firmware update is newer (1) */
		FW_VER_MASK(1 << 8),
		/* Firmware update is ongoing (1) */
		// FW_UPD_MASK (1 << 9),
		/*
		 * !< Navdata demo : (0) All navdata, (1) only navdata demo
		 */
		NAVDATA_DEMO_MASK(1 << 10),
		/*
		 * !< Navdata bootstrap : (0) options sent in all or demo mode, (1) no
		 * navdata options sent
		 */
		NAVDATA_BOOTSTRAP(1 << 11),
		/* !< Motors status : (0) Ok, (1) Motors problem */
		MOTORS_MASK(1 << 12),
		/*
		 * !< Communication Lost : (1) com problem, (0) Com is ok
		 */
		COM_LOST_MASK(1 << 13),
		/* !< VBat low : (1) too low, (0) Ok */
		VBAT_LOW(1 << 15),
		/*
		 * !< User Emergency Landing : (1) User EL is ON, (0) User EL is OFF
		 */
		USER_EL(1 << 16),
		/*
		 * !< Timer elapsed : (1) elapsed, (0) not elapsed
		 */
		TIMER_ELAPSED(1 << 17),
		/* !< Angles : (0) Ok, (1) out of range */
		ANGLES_OUT_OF_RANGE(1 << 19),
		/* !< Ultrasonic sensor : (0) Ok, (1) deaf */
		ULTRASOUND_MASK(1 << 21),
		/*
		 * !< Cutout system detection : (0) Not detected, (1) detected
		 */
		CUTOUT_MASK(1 << 22),
		/*
		 * !< PIC Version number OK : (0) a bad version number, (1) version
		 * number is OK
		 */
		PIC_VERSION_MASK(1 << 23),
		/*
		 * !< ATCodec thread ON : (0) thread OFF (1) thread ON
		 */
		ATCODEC_THREAD_ON(1 << 24),
		/*
		 * !< Navdata thread ON : (0) thread OFF (1) thread ON
		 */
		NAVDATA_THREAD_ON(1 << 25),
		/*
		 * !< Video thread ON : (0) thread OFF (1) thread ON
		 */
		VIDEO_THREAD_ON(1 << 26),
		/*
		 * !< Acquisition thread ON : (0) thread OFF (1) thread ON
		 */
		ACQ_THREAD_ON(1 << 27),
		/*
		 * !< CTRL watchdog : (1) delay in control execution (> 5ms), (0)
		 * control is well scheduled
		 */
		CTRL_WATCHDOG_MASK(1 << 28),
		/*
		 * !< ADC Watchdog : (1) delay in uart2 dsr (> 5ms), (0) uart2 is good
		 */
		ADC_WATCHDOG_MASK(1 << 29),
		/*
		 * !< Communication Watchdog : (1) com problem, (0) Com is ok
		 */
		COM_WATCHDOG_MASK(1 << 30),
		/*
		 * !< Emergency landing : (0) no emergency, (1) emergency
		 */
		EMERGENCY_MASK(1 << 31);
		int bitmask;

		private Mask(int i) {
			bitmask = i;
		}

		public int get() {
			return bitmask;
		}

	}

	private int stateValue;

	// TODO check correctness
	public void update(int stateValue) {
		this.stateValue = stateValue;
	}

	public static boolean get(Mask m, int value) {
		return (value & m.get()) != 0;
	}

	public static boolean isZero(Mask m, int value) {
		return !((value & m.get()) != 0);
	}

	public static boolean isOne(Mask m, int value) {
		return (value & m.get()) != 0;
	}

	public boolean get(Mask m) {
		return (stateValue & m.get()) != 0;
	}

}
