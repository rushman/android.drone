package de.leuphana.android.drone;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import de.leuphana.android.hello.world.R;

public class DPadView extends View {

	public DPadView(Context main) {
		super(main);

	}

	// Method for starting the drone and sending Information to View.
	public void moveForward(View view) {

		// this.control.start();

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_forward)));

	}

	// Method for landing the drone and sending Information to View.
	public void moveBackward(View view) {

		// this.control.land();

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_backward)));

	}

	// Method for turning the drone left and sending Information to View.
	public void turnLeft(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.turn_left)));

	}

	// Method for turning the drone right and sending Information to View.
	public void turnRight(View view) {

		// this.control.turnHorz(-0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.turn_right)));

	}

	// Method for turning the drone left and sending Information to View.
	public void moveLeft(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_left)));

	}

	// Method for turning the drone right and sending Information to View.
	public void moveRight(View view) {

		// this.control.turnHorz(-0.2f);

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.move_right)));

	}

	public void getOffOrDown(View view) {

		// getFlyingState

		TextView textView = (TextView) view;

		textView.setText(String.format(this.getResources().getString(R.string.get_state_down)));
	}

}
