package de.leuphana.android.drone;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DPadView extends View {

	public DPadView(Context main) {
		super(main);

	}

	// Method for starting the drone and sending Information to View.
	public void moveForward(View view) {

		// this.control.start();

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_forward)));

		Log.w("MOVEMENT", "FORWARD");

	}

	// Method for landing the drone and sending Information to View.
	public void moveBackward(View view) {

		// this.control.land();

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_backward)));

		Log.w("MOVEMENT", "BACKWARD");

	}

	// Method for turning the drone left and sending Information to View.
	public void turnLeft(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.turn_left)));

		Log.w("MOVEMENT", "TURNLEFT");
	}

	// Method for turning the drone right and sending Information to View.
	public void turnRight(View view) {

		// this.control.turnHorz(-0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.turn_right)));

		Log.w("MOVEMENT", "TURNRIGHT");

	}

	// Method for turning the drone left and sending Information to View.
	public void moveLeft(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_left)));

		Log.w("MOVEMENT", "LEFT");

	}

	// Method for turning the drone right and sending Information to View.
	public void moveRight(View view) {

		// this.control.turnHorz(-0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_right)));

		Log.w("MOVEMENT", "RIGHT");

	}

	public void getOff(View view) {

		// getFlyingState

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.get_off)));
	}

	public void getDown(View view) {

		// getFlyingState

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.get_down)));
	}

	// Method for turning the drone left and sending Information to View.
	public void balance(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.balance)));

		Log.w("MOVEMENT", "BALANCE");

	}
}
