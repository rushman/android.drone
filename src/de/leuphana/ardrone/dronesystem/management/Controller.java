package de.leuphana.ardrone.dronesystem.management;

import de.leuphana.ardrone.dronesystem.api.IConfigureDrone;
import de.leuphana.ardrone.dronesystem.api.IControlDrone;
import de.leuphana.ardrone.dronesystem.api.INavDataManager;
import de.leuphana.ardrone.dronesystem.communication.command.Init;

public class Controller {

	ConfigurationManager configManager;
	FlightManager flightManager;
	NavDataManager navDataManager;
	VideoManager videoManager;

	public IConfigureDrone getConfigManager() {
		return configManager;
	}

	public IControlDrone getFlightManager() {
		return flightManager;
	}

	public INavDataManager getNavDataManager() {
		return navDataManager;
	}

	public VideoManager getVideoManager() {
		return videoManager;
	}

	public void initialize() {
		// TODO only initialize if necessary
		configManager = new ConfigurationManager();
		flightManager = new FlightManager();
		navDataManager = new NavDataManager();
		videoManager = new VideoManager();
		Init.asyncExec(configManager.getInitSequence());
	}

}
