package de.leuphana.ardrone.dronesystem.domain;

public class DroneModel {
	private DroneDataContainer navdataContainer;

	// TODO add configuration data and video images /data
	// private Configuration config;
	// private Video video;

	public synchronized DroneDataContainer getNavdataContainer() {
		return navdataContainer;
	}

	public synchronized void setNavdataContainer(
			DroneDataContainer navdataContainer) {
		this.navdataContainer = navdataContainer;
	}

}
