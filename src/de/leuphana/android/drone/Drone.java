package de.leuphana.android.drone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import de.leuphana.android.drone.connector.DroneCommands;
import de.leuphana.android.hello.world.R;

public class Drone extends Activity {
	/** Called when the activity is first created. */

	DroneCommands control;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);

		// R = LayoutClass from res
		this.setContentView(R.layout.main);

		// ConnectToDrone connection = new ConnectToDrone("192.168.1.1");

		// this.control = new DroneCommands(connection);

	}

	// Method for starting the drone and sending Information to View.
	public void startUp(View view) {

		// this.control.start();

		TextView textView = (TextView) this.findViewById(R.id.helloTextView);

		textView.setText(String.format(this.getResources().getString(R.string.get_off)));

	}

	// Method for landing the drone and sending Information to View.
	public void getDown(View view) {

		// this.control.land();

		TextView textView = (TextView) this.findViewById(R.id.helloTextView);

		textView.setText(String.format(this.getResources().getString(R.string.get_down)));

	}

	// Method for turning the drone left and sending Information to View.
	public void turnLeft(View view) {

		// this.control.turnHorz(0.2f);

		TextView textView = (TextView) this.findViewById(R.id.helloTextView);

		textView.setText(String.format(this.getResources().getString(R.string.turn_left)));

	}

	// Method for turning the drone right and sending Information to View.
	public void turnRight(View view) {

		// this.control.turnHorz(-0.2f);

		TextView textView = (TextView) this.findViewById(R.id.helloTextView);

		textView.setText(String.format(this.getResources().getString(R.string.turn_right)));

	}
}