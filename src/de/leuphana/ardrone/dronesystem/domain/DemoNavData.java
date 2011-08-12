package de.leuphana.ardrone.dronesystem.domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import de.leuphana.ardrone.dronesystem.api.ICoordinateData;

public class DemoNavData implements ICoordinateData {
	public final static String PROPERTY_BATTERYLEVEL = "batteryLevel";

	private final PropertyChangeSupport pcs;
	public int ctrlState = 0;

	/**
	 * vbat_flying_percentage
	 */
	private int batteryLevel;
	/**
	 * theta forward-backward in milli degrees
	 */
	public float pitch; // forward backward
	/**
	 * phi //(roll) in milli degrees
	 */
	public float rotate;// phi; //(roll) in milli degrees
	/**
	 * psi; //tilt Left Right in milli degrees
	 */
	public float yaw;// psi; //tilt Left Right in milli degrees
	/**
	 * altitude in centimeters
	 */
	public int altitude;
	// VORSICHT Richtung beachten
	/**
	 * estimated linear velocity in x direction (forward/backward)
	 */
	public float vx;
	/**
	 * estimated linear velocity in y direction (up/Down)
	 */
	public float vy;
	/**
	 * estimated linear velocity in z direction (tilt-Left/Right)
	 */
	public float vz;
	// .........
	// wievielter Frame
	public int frameIndex;

	public DemoNavData() {
		pcs = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(propertyName, listener);
	}

	@Override
	public String toString() {
		return "DemoNavData [ctrlState=" + ctrlState + ", batteryLevel="
				+ getBatteryLevel() + ", pitch=" + (pitch / 1000) + ", rotate="
				+ (rotate / 1000) + ", yaw=" + (yaw / 1000) + ", altitude="
				+ altitude + ", vx=" + vx + ", vy=" + vy + ", vz=" + vz
				+ ", frameIndex=" + frameIndex + "]";
	}

	public void setBatteryLevel(int batteryLevel) {
		Object oldLevel = this.batteryLevel;
		this.batteryLevel = batteryLevel;
		pcs.firePropertyChange(PROPERTY_BATTERYLEVEL, oldLevel,
				this.batteryLevel);
	}

	public int getBatteryLevel() {
		return batteryLevel;
	}

	@Override
	public float getPitch() {
		return pitch;
	}

	@Override
	public float getRotate() {
		return rotate;
	}

	@Override
	public float getYaw() {
		return yaw;
	}

	@Override
	public int getAltitude() {
		return altitude;
	}

	@Override
	public float getVx() {
		return vx;
	}

	@Override
	public float getVy() {
		return vy;
	}

	@Override
	public float getVz() {
		return vz;
	}

	// deprecated stuff

	// komische matrizen f�r videoanalyse...

}
