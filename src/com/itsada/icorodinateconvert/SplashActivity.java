package com.itsada.icorodinateconvert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.itsada.icorodinateconvert.R;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash_layout);

		Handler x = new Handler();
		x.postDelayed(new SplashHandler(), 2000);
	}

	class SplashHandler implements Runnable {
		public void run() {

			startActivity(new Intent(getApplicationContext(), Dashboard.class));
			SplashActivity.this.finish();
		}
	}

}
