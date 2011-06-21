package de.leuphana.android.drone;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
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
	private Button button_turn_left;
	private Button button_turn_right;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
			WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		super.onCreate(savedInstanceState);

		// R = LayoutClass from res
		this.setContentView(R.layout.main);

		button_move_left = (Button) findViewById(R.id.button_move_left);
		button_move_right = (Button) findViewById(R.id.button_move_right);
		button_move_forward = (Button) findViewById(R.id.button_move_forward);
		button_move_backward = (Button) findViewById(R.id.button_move_backward);
		button_turn_left = (Button) findViewById(R.id.button_turn_left);
		button_turn_right = (Button) findViewById(R.id.button_turn_right);

		imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setOnTouchListener(this);

		textView = (TextView) findViewById(R.id.textView);
		dPadView = new DPadView(this);

		// ConnectToDrone connection = new ConnectToDrone("192.168.1.1");

		// this.control = new DroneCommands(connection);

	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {

		int eventId = event.getAction();

		if (eventId == 0 || eventId == 5 || eventId == 261 || eventId == 517 || eventId == 2) {

			for (int i = 0; i < event.getPointerCount(); i++) {

				int x = (int) event.getX(i);
				int y = (int) event.getY(i);

				if (x >= 50 && x <= 150 && y >= 280 && y <= 380) {
					button_turn_left.setPressed(true);
					dPadView.turnLeft(textView);
					button_turn_right.setPressed(false);
				} else if (x >= 200 && x <= 300 && y >= 280 && y <= 380) {
					button_turn_right.setPressed(true);
					dPadView.turnRight(textView);
					button_turn_left.setPressed(false);
				} else if (x >= 550 && x <= 650 && y >= 280 && y <= 380 && !gyro) {
					button_move_left.setPressed(true);
					dPadView.moveLeft(textView);
					button_move_right.setPressed(false);
				} else if (x >= 700 && x <= 800 && y >= 280 && y <= 380 && !gyro) {
					button_move_right.setPressed(true);
					dPadView.moveRight(textView);
					button_move_left.setPressed(false);
				} else if (x >= 600 && x <= 800 && y >= 180 && y <= 279 && !gyro) {
					button_move_forward.setPressed(true);
					dPadView.moveForward(textView);
					button_move_backward.setPressed(false);
				} else if (x >= 600 && x <= 800 && y >= 381 && y <= 500 && !gyro) {
					button_move_backward.setPressed(true);
					dPadView.moveBackward(textView);
					button_move_forward.setPressed(false);
				}

			}
		} else if (eventId == 1 || eventId == 6 || eventId == 262 || eventId == 518) {

			button_move_left.setPressed(false);
			button_move_right.setPressed(false);
			button_turn_left.setPressed(false);
			button_turn_right.setPressed(false);
			button_move_forward.setPressed(false);
			button_move_backward.setPressed(false);
		}

		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		Log.w("accuracy", "blkfopvfjspjvpsj");
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		if (gyro) {
			updateOrientation(event.values[0], event.values[1]);
		}
	}

	public void getOffOrDown(View view) {

		ToggleButton flyingButton = (ToggleButton) findViewById(R.id.button_start_land);
		if (flyingButton.isChecked()) {
			dPadView.getOff(textView);
		} else {
			dPadView.getDown(textView);
		}
	}

	public void gyroOnOff(View view) {

		ToggleButton gyroButton = (ToggleButton) findViewById(R.id.button_gyro);
		if (gyroButton.isChecked()) {
			gyro = true;
		} else {
			gyro = false;
		}
	}

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
			dPadView.balance(textView);
		}

	}
}