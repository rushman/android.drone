package de.leuphana.android.drone;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import de.leuphana.android.drone.connector.DroneCommands;

/**
 * Drone class as initial activity. It offers the OnTouchListener and the SensorEventListener to
 * grab events and process them.
 * 
 * @author Arne Passow
 * 
 */
public class Drone extends Activity implements OnTouchListener, SensorEventListener {

	private DroneCommands control;
	private DPadView dPadView;
	private ImageView imageView;
	private TextView textView;
	private SensorManager sensorManager;
	private Sensor accelerometer;
	private boolean gyro;
	private Button button_move_left;
	private Button button_move_right;
	private Button button_move_forward;
	private Button button_move_backward;
	private Button button_move_up;
	private Button button_move_down;
	private Button button_turn_left;
	private Button button_turn_right;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		// different WindowLayouts
		// TODO: ignore Hardwarebuttons ?
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
			WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// get SensorManager
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		// get Accelerometersensor
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		// R = LayoutClass from res
		this.setContentView(R.layout.main);

		// Buttons from MainView
		button_move_left = (Button) findViewById(R.id.button_move_left);
		button_move_right = (Button) findViewById(R.id.button_move_right);
		button_move_forward = (Button) findViewById(R.id.button_move_forward);
		button_move_backward = (Button) findViewById(R.id.button_move_backward);
		button_move_up = (Button) findViewById(R.id.button_move_up);
		button_move_down = (Button) findViewById(R.id.button_move_down);
		button_turn_left = (Button) findViewById(R.id.button_turn_left);
		button_turn_right = (Button) findViewById(R.id.button_turn_right);

		// get ImageView
		imageView = (ImageView) findViewById(R.id.imageView);

		// set TouchListener to the ImageView which lies on the main view
		imageView.setOnTouchListener(this);

		// get TextView for
		textView = (TextView) findViewById(R.id.textView);

		dPadView = new DPadView(this);

		super.onCreate(savedInstanceState);

		// ConnectToDrone connection = new ConnectToDrone("192.168.1.1");
		// this.control = new DroneCommands(connection);

	}

	/**
	 * Method to grab the TouchEvents from the view. It starts the corresponding methods, if the
	 * correct area is touched. It reacts to different eventIds up, down and move corresponding to
	 * the different pointers(fingers).
	 */
	@Override
	public boolean onTouch(View view, MotionEvent event) {

		int eventId = event.getAction();

		// ids for the different TouchEvents we want to react to
		if (eventId == 0 || eventId == 2 || eventId == 5 || eventId == 261 || eventId == 517) {

			// iterate through the different pointers(fingers)
			for (int i = 0; i < event.getPointerCount(); i++) {

				// x and y coordinates from the current pointer
				int x = (int) event.getX(i);
				int y = (int) event.getY(i);

				// Log.w("POINTER " + i, "X= " + x + " Precision " + event.getXPrecision() + " Y= "
				// + y + " Precision " + event.getYPrecision() + " " + event.getRawY());

				// send setPressed to the corresponding button so that the picture in the main View
				// changes to active and deactivate the button towards.
				if (x >= 50 && x <= 130 && y >= 280 && y <= 380) {
					button_turn_left.setPressed(true);
					dPadView.turnLeft(textView);
					button_turn_right.setPressed(false);
				} else if (x >= 230 && x <= 300 && y >= 280 && y <= 380) {
					button_turn_right.setPressed(true);
					dPadView.turnRight(textView);
					button_turn_left.setPressed(false);
				} else if (x >= 100 && x <= 230 && y >= 180 && y <= 300) {
					button_move_up.setPressed(true);
					dPadView.moveUp(textView);
					button_move_down.setPressed(false);
				} else if (x >= 100 && x <= 230 && y >= 381 && y <= 500) {
					button_move_down.setPressed(true);
					dPadView.moveDown(textView);
					button_move_up.setPressed(false);
				} else if (x >= 550 && x <= 630 && y >= 280 && y <= 380 && !gyro) {
					button_move_left.setPressed(true);
					dPadView.moveLeft(textView);
					button_move_right.setPressed(false);
				} else if (x >= 720 && x <= 800 && y >= 280 && y <= 380 && !gyro) {
					button_move_right.setPressed(true);
					dPadView.moveRight(textView);
					button_move_left.setPressed(false);
				} else if (x >= 600 && x <= 730 && y >= 180 && y <= 300 && !gyro) {
					button_move_forward.setPressed(true);
					dPadView.moveForward(textView);
					button_move_backward.setPressed(false);
				} else if (x >= 600 && x <= 730 && y >= 381 && y <= 500 && !gyro) {
					button_move_backward.setPressed(true);
					dPadView.moveBackward(textView);
					button_move_forward.setPressed(false);
				}

			}
		}

		// ids for touchUp or release events put the buttons into unpressed state
		else if (eventId == 1 || eventId == 6 || eventId == 262 || eventId == 518) {

			button_move_left.setPressed(false);
			button_move_right.setPressed(false);
			button_turn_left.setPressed(false);
			button_turn_right.setPressed(false);
			button_move_up.setPressed(false);
			button_move_down.setPressed(false);
			button_move_forward.setPressed(false);
			button_move_backward.setPressed(false);
		}

		return true;
	}

	/**
	 * Register the sensorListener onResume.
	 */
	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
	}

	/**
	 * Unregister the sensorListener to avoid battery leak.
	 */
	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	/**
	 * Called if Accuracy changes. Not used.
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	/**
	 * Called if Sensor changes. Fires updateOrientation with the x and Y values if gyro == true.
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {

		if (gyro) {
			updateOrientation(event.values[0], event.values[1]);
		}
	}

	/**
	 * Calls dPadView getOff oder getDown corresponding to the flyingButton state.
	 * 
	 * @param view
	 */
	public void getOffOrDown(View view) {

		ToggleButton flyingButton = (ToggleButton) findViewById(R.id.button_start_land);
		if (flyingButton.isChecked()) {
			dPadView.getOff(textView);
		} else {
			dPadView.getDown(textView);
		}
	}

	/**
	 * Calls dPadView panic.
	 * 
	 * @param view
	 */
	public void panic(View view) {

		dPadView.panic(textView);
	}

	/**
	 * Sets gyro true or falls corresponding to the gyroButton state.
	 * 
	 * @param view
	 */
	public void gyroOnOff(View view) {

		ToggleButton gyroButton = (ToggleButton) findViewById(R.id.button_gyro);
		if (gyroButton.isChecked()) {
			gyro = true;
		} else {
			gyro = false;
		}
	}

	/**
	 * Fires the different methods corresponding to the Accelerometersensor values. Values between
	 * -2 and 2 hover the drone.
	 * 
	 * @param y
	 * @param x
	 */
	private void updateOrientation(float y, float x) {

		if (x < -2 && y <= 2 && y >= -2) {
			dPadView.moveLeft(textView);
		} else if (x > 2 && y <= 2 && y >= -2) {
			dPadView.moveRight(textView);
		} else if (y < -2 && x <= 2 && x >= -2) {
			dPadView.moveForward(textView);
		} else if (y > 2 && x <= 2 && x >= -2) {
			dPadView.moveBackward(textView);
		} else if (x < -2 && y < -2) {
			dPadView.moveLeft(textView);
			dPadView.moveForward(textView);
		} else if (x < -2 && y > 2) {
			dPadView.moveLeft(textView);
			dPadView.moveBackward(textView);
		} else if (x > 2 && y < -2) {
			dPadView.moveRight(textView);
			dPadView.moveForward(textView);
		} else if (x > 2 && y > 2) {
			dPadView.moveRight(textView);
			dPadView.moveBackward(textView);
		} else {
			dPadView.hover(textView);
		}

	}
}