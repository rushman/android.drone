package de.leuphana.ardrone.dronesystem.management;

import java.text.MessageFormat;

import de.leuphana.ardrone.dronesystem.api.IControlDrone;
import de.leuphana.ardrone.dronesystem.communication.command.Commander;
import de.leuphana.ardrone.dronesystem.domain.CmdValue;
import de.leuphana.ardrone.dronesystem.domain.Command;

public class FlightManager implements IControlDrone {

	private Float tilt = 0f;
	private Float vertical = 0f, horizontal = 0f, rotation = 0f;
	private int baseSpeed = 100;
	private int horzSpeed = 3;
	private int verticalSpeed = 1;
	private int tiltSpeed = 3;
	private int rotationSpeed = 1;

	public FlightManager() {
	}

	@Override
	public void hover(boolean pressed) {
		Commander.sendInstantCommand(new Command(CmdValue.COMMAND_HOVER));
	}

	@Override
	public void start() {
		Commander.setCommand(CmdValue.COMMAND_START);
	}

	@Override
	public void land() {
		Commander.setCommand(CmdValue.COMMAND_START);
	}

	@Override
	public void tiltLeft(boolean pressed) {
		Float f = -speedToFloat(tiltSpeed);
		if (!pressed) {
			f = 0f;
		}
		moveInternal(f, null, null, null);

	}

	@Override
	public void tiltRight(boolean pressed) {
		Float f = speedToFloat(tiltSpeed);
		if (!pressed) {
			f = 0f;
		}
		moveInternal(f, null, null, null);

	}

	@Override
	public void up(boolean pressed) {
		Float f = speedToFloat(verticalSpeed);
		if (!pressed) {
			f = 0f;
		}
		moveInternal(null, f, null, null);
	}

	@Override
	public void down(boolean pressed) {
		Float f = -speedToFloat(verticalSpeed);
		if (!pressed) {
			f = 0f;
		}
		moveInternal(null, f, null, null);
	}

	@Override
	public void forward(boolean pressed) {
		Float f = -speedToFloat(horzSpeed);
		if (!pressed) {
			f = 0f;
		}
		moveInternal(null, null, f, null);
	}

	@Override
	public void backward(boolean pressed) {
		Float f = speedToFloat(horzSpeed);
		if (!pressed) {
			f = 0f;
		}
		moveInternal(null, null, f, null);
	}

	public void rotLeft(boolean pressed) {
		Float f = -speedToFloat(rotationSpeed);
		if (!pressed) {
			f = 0f;
		}
		moveInternal(null, null, null, f);
	}

	public void rotRight(boolean pressed) {
		Float f = speedToFloat(rotationSpeed);
		if (!pressed) {
			f = 0f;
		}
		moveInternal(null, null, null, f);
	}

	@Override
	public void reset() {
		Commander.setCommand(CmdValue.COMMAND_RESETWATCHDOG);

	}

	@Override
	public void move(Float tilt, Float vertical, Float horizontal, Float rotation) {
		this.tilt = (tilt == null) ? this.tilt : tilt;
		this.vertical = (vertical == null) ? this.vertical : vertical;
		this.horizontal = (horizontal == null) ? this.horizontal : horizontal;
		this.rotation = (rotation == null) ? this.rotation : rotation;
		String tiltString = valueAndSpeedToString(tiltSpeed, this.tilt);
		String verticalString = valueAndSpeedToString(verticalSpeed, this.vertical);
		String horz = valueAndSpeedToString(horzSpeed, this.horizontal);
		String rot = valueAndSpeedToString(rotationSpeed, this.rotation);

		Command command = new Command(CmdValue.COMMAND_NAV);
		int mode = ConfigurationManager.getConfiguration().isInGameMode() ? 0 : 1;
		command.replace(mode, tiltString, horz, verticalString, rot);
		String name = MessageFormat.format(
			"MOVE tilt: {0},horizontal: {1},vertical: {2},rotate: {3}", this.tilt, this.horizontal,
			this.vertical, this.rotation);
		command.info = name;
		Commander.setCommand(command);
	}

	/**
	 * 
	 * @param tilt negative moves left, positive moves right
	 * @param vertical positive higher, negative lower
	 * @param horizontal negative forward, positive backward
	 * @param rotation negative turns left, positive turns to right
	 */
	protected void moveInternal(Float tilt, Float vertical, Float horizontal, Float rotation) {
		// TODO: if started / check state
		Command command = new Command(CmdValue.COMMAND_NAV);
		this.tilt = (tilt == null) ? this.tilt : tilt;
		this.vertical = (vertical == null) ? this.vertical : vertical;
		this.horizontal = (horizontal == null) ? this.horizontal : horizontal;
		this.rotation = (rotation == null) ? this.rotation : rotation;
		int mode = ConfigurationManager.getConfiguration().isInGameMode() ? 0 : 1;
		command.replace(mode, toIntString(tilt), toIntString(horizontal), toIntString(vertical),
			toIntString(rotation));
		String name = MessageFormat.format(
			"MOVE tilt: {0},horizontal: {1},vertical: {2},rotate: {3}", this.tilt, this.horizontal,
			this.vertical, this.rotation);
		command.info = name;
		Commander.setCommand(command);
	}

	public int getBaseSpeed() {
		return baseSpeed;
	}

	@Override
	public void setBaseSpeed(int baseSpeed) {
		int old = this.baseSpeed;
		this.baseSpeed = ensureIntInRange(baseSpeed);
		// firePropertyChange("baseSpeed", old, baseSpeed);
	}

	public int getHorzSpeed() {
		return horzSpeed;
	}

	public void setHorzSpeed(int horzSpeed) {
		int old = this.horzSpeed;
		this.horzSpeed = ensureIntInRange(horzSpeed);
		// firePropertyChange("horzSpeed", old, horzSpeed);
	}

	public int getVerticalSpeed() {
		return verticalSpeed;
	}

	public void setVerticalSpeed(int verticalSpeed) {
		int old = this.verticalSpeed;
		this.verticalSpeed = ensureIntInRange(verticalSpeed);
		// firePropertyChange("verticalSpeed", old, verticalSpeed);
	}

	public int getTiltSpeed() {
		return tiltSpeed;
	}

	public void setTiltSpeed(int tiltSpeed) {
		int old = this.tiltSpeed;
		this.tiltSpeed = ensureIntInRange(tiltSpeed);
		// firePropertyChange("tiltSpeed", old, tiltSpeed);
	}

	public int getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(int rotationSpeed) {
		int old = this.rotationSpeed;
		this.rotationSpeed = ensureIntInRange(rotationSpeed);
		// firePropertyChange("rotationSpeed", old, rotationSpeed);
	}

	private float speedToFloat(int speed) {
		return ((baseSpeed * speed) / 10.0f) / 100.0f;
	}

	private String valueAndSpeedToString(int speed, float value) {
		return toIntString(ensureFloatInRange((speedToFloat(speed) * value), -1f, 1f));
	}

	private String toIntString(float f) {
		int intBits = Float.floatToIntBits(f);
		return Integer.toString(intBits);
	}

	private int ensureIntInRange(int integer) {
		return ensureIntInRange(integer, 0, 100);
	}

	private float ensureFloatInRange(float value, float lower, float upper) {
		float zahl;
		if (value > upper)
			zahl = upper;
		else if (lower < value)
			zahl = lower;
		else
			zahl = value;
		return zahl;
	}

	private int ensureIntInRange(int integer, int lower, int upper) {
		int zahl;
		if (integer > upper)
			zahl = 100;
		else if (lower < 0)
			zahl = 0;
		else
			zahl = integer;
		return zahl;
	}
}
