package de.leuphana.ardrone.dronesystem.domain;

import java.util.ArrayList;
import java.util.List;

import de.leuphana.ardrone.dronesystem.api.INavDataOption;

/**
 * NavData container holds list of options as well as drone status
 * 
 * @author Florian Quadt
 * 
 */
public class DroneDataContainer {
	private final List<INavDataOption> options;
	private int seqNumber;
	private int stateValues;
	private int visionFlag;

	public DroneDataContainer() {

		options = new ArrayList<INavDataOption>();
	}

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}

	/**
	 * vision flag is basically an integer. Eventually has to be interpreted by the parser
	 * 
	 * @return
	 */
	public int getVisionFlag() {
		return visionFlag;
	}

	public int getStateValues() {
		return stateValues;
	}

	public void setStateValues(int stateValues) {
		this.stateValues = stateValues;
	}

	public boolean hasOptions() {
		/* NOT empty */
		return !options.isEmpty();
	}

	public boolean contains(Object o) {
		return options.contains(o);
	}

	public boolean add(INavDataOption e) {
		return options.add(e);
	}

	public boolean addAll(List<INavDataOption> options) {
		return options.addAll(options);
	}

	public void clear() {
		options.clear();
	}

	public List<INavDataOption> getOptions() {
		return options;
	}

	public void setVisionFlag(int int1) {
		// TODO Auto-generated method stub

	}

}
