package de.leuphana.ardrone.dronesystem.api;

import java.util.EventListener;

public interface MessageListener extends EventListener{
	
	public void commandSend(String commandName, String message, boolean success);

}
