package de.leuphana.android.drone;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Provides Methods for DroneControl which start movement. Extends View to print corresponding
 * messages in TextView on main View.
 * 
 * @author Arne Passow
 * 
 */
public class DPadView extends View {

	public DPadView(Context main) {
		super(main);

	}

	/**
	 * Method to move the drone forward and sending Information to View.
	 * 
	 * @param view
	 */
	public void moveForward(View view) {

		// this.control.start();

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_forward)));

		Log.w("MOVEMENT", "FORWARD");

	}

	/**
	 * Method to move the drone backward and sending Information to View.
	 * 
	 * @param view
	 */
	public void moveBackward(View view) {

		// this.control.land();

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_backward)));

		Log.w("MOVEMENT", "BACKWARD");

	}

	/**
	 * Method to turn the drone left and sending Information to View.
	 * 
	 * @param view
	 */
	public void turnLeft(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.turn_left)));

		Log.w("MOVEMENT", "TURNLEFT");
	}

	/**
	 * Method to turn the drone right and sending Information to View.
	 * 
	 * @param view
	 */
	public void turnRight(View view) {

		// this.control.turnHorz(-0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.turn_right)));

		Log.w("MOVEMENT", "TURNRIGHT");

	}

	/**
	 * Method to move the drone left and sending Information to View.
	 * 
	 * @param view
	 */
	public void moveLeft(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_left)));

		Log.w("MOVEMENT", "LEFT");

	}

	/**
	 * Method to move the drone right and sending Information to View.
	 * 
	 * @param view
	 */
	public void moveRight(View view) {

		// this.control.turnHorz(-0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_right)));

		Log.w("MOVEMENT", "RIGHT");

	}

	/**
	 * Method to start the drone and sending Information to View.
	 * 
	 * @param view
	 */
	public void getOff(View view) {

		// getFlyingState

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.get_off)));
	}

	/**
	 * Method to land the drone and sending Information to View.
	 * 
	 * @param view
	 */
	public void getDown(View view) {

		// getFlyingState

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.get_down)));
	}

	/**
	 * Method to balance/hover the drone and sending Information to View.
	 * 
	 * @param view
	 */
	public void hover(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.hover)));

		Log.w("MOVEMENT", "HOVER");

	}

	/**
	 * Method to moveUp the drone and sending Information to View.
	 * 
	 * @param view
	 */
	public void moveUp(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_up)));

		Log.w("MOVEMENT", "UP");

	}

	/**
	 * Method to moveDown the drone and sending Information to View.
	 * 
	 * @param view
	 */
	public void moveDown(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_down)));

		Log.w("MOVEMENT", "DOWN");

	}

	/**
	 * Method to shutdown the drone immediately.
	 * 
	 * @param view
	 */
	public void panic(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.panic)));

		Log.w("DRONE", "PANIC");

	}
}
