package de.leuphana.android.drone.connector;

public class DroneCommands {

	private final static String COMMAND_CONF_MAXHIGHT = "AT*REF= AT*CONFIG=%1$,\"control:altitude_max\",\"800\"";
	private final static String COMMAND_START = "AT*REF=%1$d,290718208";
	private final static String COMMAND_LAND = "AT*REF=%1$d,290717696";
	private final static String COMMAND_HOVER = "AT*PCMD=%1$d,1,0,0,0,0";
	private final static String COMMAND_HOVER_BACK = "AT*PCMD=%1$d,0,0,0,0,0";
	private final static String COMMAND_NAV = "AT*PCMD=%1$d,0,%2$d,%3$d,%4$d,%5$d";

	ConnectToDrone connection;
	private long counter = 1;

	public DroneCommands(ConnectToDrone connection) {

		this.connection = connection;
		this.connection.sendCommand(COMMAND_CONF_MAXHIGHT);
		this.getCounter();
	}

	public void hover() {
		this.send(this.addCounter(COMMAND_HOVER));
	}

	public void turnHorz(float f) {

		String formattedMessage = String.format(COMMAND_NAV, this.getCounter(), 0, 0, 0,
			Float.floatToIntBits(f));
		this.send(formattedMessage);

	}

	public void turn(int direction, float f) {
		Float.floatToIntBits(f);

	}

	public void reset() {
		this.land();
	}

	public boolean start() {
		return this.send(this.addCounter(COMMAND_START));
	}

	public boolean land() {
		return this.send(this.addCounter(COMMAND_LAND));
	}

	public boolean send(String command) {
		return this.connection.sendCommand(command);
	}

	private String addCounter(String command) {

		return String.format(command, this.getCounter());
	}

	private long getCounter() {
		return this.counter++;
	}
}
