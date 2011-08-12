package de.leuphana.ardrone.dronesystem.api;

import de.leuphana.ardrone.dronesystem.management.NavDataNotAvailableException;

public interface INavDataManager {
	/**
	 * TODO javadoc
	 * 
	 * @return
	 * @throws NavDataNotAvailableException
	 */
	public ICoordinateData getDemoNavData() throws NavDataNotAvailableException;

}
