package com.itsada.icorodinateconvert;

import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.itsada.icorodinateconvert.R;
//import com.itsada.imoney.models.Account;
//import com.itsada.imoney.models.AccountType;

@SuppressLint({ "NewApi", "SimpleDateFormat", "InflateParams" })
public abstract class BaseActivity extends Activity {

	protected String TAG = BaseActivity.class.getName();
	protected TextView textTitle;
	protected DecimalFormat df = new DecimalFormat("#.#######");

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		overridePendingTransition(R.animator.activity_open_translate,
				R.animator.activity_close_scale);

		// Update resource on change local
		if (App.configuration != null)
			if (!getResources().getConfiguration().locale
					.equals(App.configuration.getLocale()))
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Log.d(TAG, "Update Local");
						getResources().updateConfiguration(
								App.resourceConfig,
								getBaseContext().getResources()
										.getDisplayMetrics());
					}
				});
	}

	protected void hideKeyboard(View v) {

		try {

			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

		} catch (Exception e) {
			Log.e(TAG, "hideKeyboard : " + e.toString());
		}

	}

	protected void setActionBar() {

		try {

			ActionBar actionBar = (ActionBar) getActionBar();
			actionBar.setDisplayShowHomeEnabled(false);
			actionBar.setDisplayShowTitleEnabled(false);

			LayoutInflater mInflater = LayoutInflater.from(this);
			View mCustomView = mInflater.inflate(R.layout.custom_actionbar,
					null);
			
			textTitle = (TextView) mCustomView.findViewById(R.id.tvTitle);			
			textTitle.setText(getString(R.string.app_name));
			
			actionBar.setCustomView(mCustomView);
			actionBar.setDisplayShowCustomEnabled(true);

		} catch (Exception e) {
			Log.e(TAG, "setActionBar:" + e.toString());
		}

	}


	@Override
	public void onBackPressed() {

		super.onBackPressed();

		overridePendingTransition(R.animator.activity_open_scale,
				R.animator.activity_close_translate);

		finish();
	}

	@Override
	public void finish() {

		super.finish();

		overridePendingTransition(R.animator.activity_open_scale,
				R.animator.activity_close_translate);
	}

}
