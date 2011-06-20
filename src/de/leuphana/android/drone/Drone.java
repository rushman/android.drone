package de.leuphana.android.drone;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import de.leuphana.android.drone.connector.DroneCommands;
import de.leuphana.android.hello.world.R;

public class Drone extends Activity implements OnTouchListener {

	DroneCommands control;
	DPadView dPadView;
	ImageView imageView;
	TextView textView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
			WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		super.onCreate(savedInstanceState);

		// R = LayoutClass from res
		this.setContentView(R.layout.main);

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

		Button button_move_left = (Button) findViewById(R.id.button_move_left);
		Button button_move_right = (Button) findViewById(R.id.button_move_right);
		Button button_move_forward = (Button) findViewById(R.id.button_move_forward);
		Button button_move_backward = (Button) findViewById(R.id.button_move_backward);
		Button button_turn_left = (Button) findViewById(R.id.button_turn_left);
		Button button_turn_right = (Button) findViewById(R.id.button_turn_right);

		if (eventId == 0 || eventId == 5 || eventId == 261 || eventId == 517 || eventId == 2) {

			for (int i = 0; i < event.getPointerCount(); i++) {

				int x = (int) event.getX(i);
				int y = (int) event.getY(i);

				if (x >= 60 && x <= 110 && y >= 280 && y <= 380) {
					button_move_left.setPressed(true);
					dPadView.moveLeft(textView);
					button_move_right.setPressed(false);
				} else if (x >= 220 && x <= 270 && y >= 280 && y <= 380) {
					button_move_right.setPressed(true);
					dPadView.moveRight(textView);
					button_move_left.setPressed(false);
				} else if (x >= 560 && x <= 610 && y >= 280 && y <= 380) {
					button_turn_left.setPressed(true);
					dPadView.turnLeft(textView);
					button_turn_right.setPressed(false);
				} else if (x >= 720 && x <= 770 && y >= 280 && y <= 380) {
					button_turn_right.setPressed(true);
					dPadView.turnRight(textView);
					button_turn_left.setPressed(false);
				} else if (x >= 600 && x <= 800 && y >= 240 && y <= 320) {
					button_move_forward.setPressed(true);
					dPadView.moveForward(textView);
					button_move_backward.setPressed(false);
				} else if (x >= 600 && x <= 800 && y >= 350 && y <= 500) {
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

}