package de.leuphana.ardrone.dronesystem.domain;

public class Configuration {

	private boolean gameMode = false;
	private boolean outdoor = false;
	private boolean shell = true;
	private float maxEulerAngle = 0.25f;
	private float maxVz = 2000.0f;
	private float maxYaw = 2.0f;
	private int maxHight = 1300;

	public void setGameMode(boolean gameMode) {
		this.gameMode = gameMode;
	}

	public void setOutdoor(boolean outdoor) {
		this.outdoor = outdoor;
	}

	public void setShell(boolean shell) {
		this.shell = shell;
	}

	public void setMaxEulerAngle(float maxEulerAngle) {
		this.maxEulerAngle = maxEulerAngle;
	}

	public void setMaxVz(float maxVz) {
		this.maxVz = maxVz;
	}

	public void setMaxYaw(float maxYaw) {
		this.maxYaw = maxYaw;
	}

	public void setMaxHight(int maxHight) {
		this.maxHight = maxHight;
	}

	public boolean isInGameMode() {
		return gameMode;
	}

	public boolean isOutdoor() {
		return outdoor;
	}

	public boolean isShell() {
		return shell;
	}

	public float getMaxEulerAngle() {
		return maxEulerAngle;
	}

	public float getMaxVz() {
		return maxVz;
	}

	public float getMaxYaw() {
		return maxYaw;
	}

	public int getMaxHight() {
		return maxHight;
	}
}
