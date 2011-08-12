package de.leuphana.ardrone.dronesystem.api;

public interface IControlDrone {

	public void hover(boolean pressed);

	public void start();

	public void land();

	public void tiltLeft(boolean pressed);

	public void tiltRight(boolean pressed);

	public void up(boolean pressed);

	public void down(boolean pressed);

	public void forward(boolean pressed);

	public void backward(boolean pressed);

	public void reset();

	/**
	 * @param tilt
	 *            : moveLeft(-1) / moveRight(1)
	 * @param vertical
	 *            : up(1) / down(-1) (moveUp / moveDown)
	 * @param horizontal
	 *            : backward(1) / forward(-1)
	 * @param rotation
	 *            : turnLeft(-1) / turnRight(1)
	 */
	public void move(Float tilt, Float vertical, Float horizontal,
			Float rotation);

	public void setBaseSpeed(int baseSpeed);

}
