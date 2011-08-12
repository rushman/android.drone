package de.leuphana.ardrone.dronesystem.video;

import java.util.EventListener;

public interface ImageUpdateListener extends EventListener {

	public void onUpdate(UpdateEvent event);

}
