package de.leuphana.ardrone.dronesystem.management;

import static de.leuphana.ardrone.dronesystem.domain.CmdValue.CONF_EULER_ANGLE;
import static de.leuphana.ardrone.dronesystem.domain.CmdValue.CONF_YAW;
import de.leuphana.ardrone.dronesystem.api.IConfigureDrone;
import de.leuphana.ardrone.dronesystem.domain.CmdValue;
import de.leuphana.ardrone.dronesystem.domain.Command;
import de.leuphana.ardrone.dronesystem.domain.Configuration;

@SuppressWarnings("unused")
public class ConfigurationManager implements IConfigureDrone {

	static Configuration configuration;

	public ConfigurationManager() {
		configuration = new Configuration();
	}

	@Override
	public void setGameMode(boolean flag) {
		throw new UnsupportedOperationException();
		// TODO Auto-generated method stub

	}

	public static Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public void setOutdoor(boolean flag) {
		throw new UnsupportedOperationException();
		// TODO Auto-generated method stub

	}

	@Override
	public void setIndoorShell(boolean flag) {
		throw new UnsupportedOperationException();
		// TODO Auto-generated method stub

	}

	public Command[] getInitSequence() {
		return new Command[] { new Command(CmdValue.COMMAND_RESETWATCHDOG),
				addMaxHightCommand(configuration.getMaxHight()),
				addMaxYawCommand(configuration.getMaxYaw()),
				addMaxEulerAngleCommand(configuration.getMaxEulerAngle()),
				addMaxVzCommand(configuration.getMaxVz()),
				new Command(CmdValue.COMMAND_CONF_TRIM) };
	}

	protected Command addMaxHightCommand(int hight) {
		Command command = new Command(CmdValue.COMMAND_CONF_MAXHIGHT);
		command.replace(Float.toString(hight));
		return command;
	}

	protected Command addMaxYawCommand(float yaw) {
		Command command = new Command(CONF_YAW);
		command.replace(Float.toString(yaw));
		return command;
	}

	protected Command addMaxEulerAngleCommand(float eulerAngle) {
		Command command = new Command(CONF_EULER_ANGLE);
		command.replace(Float.toString(eulerAngle));
		return command;
	}

	protected Command addMaxVzCommand(float maxvz) {
		Command command = new Command(CmdValue.CONF_VZ_MAX);
		command.replace(Float.toString(maxvz));
		return command;
	}

}
