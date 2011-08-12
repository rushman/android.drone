package de.leuphana.ardrone.dronesystem.management;

import java.util.List;

import de.leuphana.ardrone.dronesystem.api.ICoordinateData;
import de.leuphana.ardrone.dronesystem.api.INavDataManager;
import de.leuphana.ardrone.dronesystem.api.INavDataOption;
import de.leuphana.ardrone.dronesystem.communication.command.NavData;
import de.leuphana.ardrone.dronesystem.domain.DemoNavData;
import de.leuphana.ardrone.dronesystem.domain.DroneModel;

public class NavDataManager implements INavDataManager {

	protected DroneModel droneModel;
	private DemoNavData oldDemoNavData;

	public NavDataManager() {
		NavData.start();
	}

	@Override
	public ICoordinateData getDemoNavData() throws NavDataNotAvailableException {
		if (droneModel.getNavdataContainer().hasOptions()) {
			List<INavDataOption> options = droneModel.getNavdataContainer()
					.getOptions();
			for (INavDataOption demoNavData : options) {
				if (demoNavData instanceof DemoNavData) {
					oldDemoNavData = (DemoNavData) demoNavData;
					break;
				}
			}
		}
		if (oldDemoNavData == null)
			throw new NavDataNotAvailableException(
					"Navdata currently not available because navData receiving not initialized");
		return oldDemoNavData;
	}
}
