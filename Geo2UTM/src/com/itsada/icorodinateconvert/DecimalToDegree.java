package com.itsada.icorodinateconvert;

import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.itsada.framework.cooridate.ConvertCoordinate;
import com.itsada.geo2utm.core.DecimalGeoCoordinate;
import com.itsada.icorodinateconvert.R;

public class DecimalToDegree extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decimal_to_degree);

		setActionBar();

		textTitle.setText("Decimal to Degree");

		textTitle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		final EditText etLat = (EditText) findViewById(R.id.etLatitude);
		final EditText etLon = (EditText) findViewById(R.id.etLongitude);

		final TextView tvLat = (TextView) findViewById(R.id.tvLatitude);
		final TextView tvLon = (TextView) findViewById(R.id.tvLongitude);

		final TextView tvLatMessage = (TextView) findViewById(R.id.tvLatitudeMessage);
		final TextView tvLonMessage = (TextView) findViewById(R.id.tvLongitudeMessage);

		etLat.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.equals(""))
					tvLatMessage.setVisibility(View.VISIBLE);
				else
					tvLatMessage.setVisibility(View.GONE);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		etLon.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.equals(""))
					tvLonMessage.setVisibility(View.VISIBLE);
				else
					tvLonMessage.setVisibility(View.GONE);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		Button bt = (Button) findViewById(R.id.btConvert);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// ConvertCoordinate convertCoordinate = new
				// ConvertCoordinate();
				// tvLat.setText(convertCoordinate.convertDecimalToDegree(45.3470));
				if (etLat.getText().toString().equals("")
						|| etLon.getText().toString().equals("")) {
					if (etLat.getText().toString().equals(""))
						tvLatMessage.setVisibility(View.VISIBLE);
					else
						tvLatMessage.setVisibility(View.GONE);
					if (etLon.getText().toString().equals(""))
						tvLonMessage.setVisibility(View.VISIBLE);
					else
						tvLonMessage.setVisibility(View.GONE);
				} else {

					DecimalGeoCoordinate decimalGeoCoordinate = new DecimalGeoCoordinate(
							etLat.getText().toString(), etLon.getText()
									.toString());
					if (decimalGeoCoordinate.validate()) {
						if (decimalGeoCoordinate.latitudeIsValid()) {
							tvLatMessage
									.setText("The latitude is valid, please check.");
							tvLatMessage.setVisibility(View.VISIBLE);
						}
						if (decimalGeoCoordinate.longitudeIsValid()) {
							tvLonMessage
									.setText("The longitude is valid, please check.");
							tvLonMessage.setVisibility(View.VISIBLE);
						}
					} else {
						tvLatMessage.setVisibility(View.GONE);
						tvLonMessage.setVisibility(View.GONE);
						tvLat.setText(String.valueOf(decimalGeoCoordinate
								.getLatitude()));
						tvLon.setText(String.valueOf(decimalGeoCoordinate
								.getLongitude()));
					}
					// tvLatMessage.setVisibility(View.GONE);
					// tvLonMessage.setVisibility(View.GONE);
					//
					// tvLat.setText(convertCoordinate
					// .convertDecimalToDegree(Double.parseDouble(etLat
					// .getText().toString())));
					// tvLon.setText(convertCoordinate
					// .convertDecimalToDegree(Double.parseDouble(etLon
					// .getText().toString())));
				}
			}
		});

		Location location = new Location("AdMobProvider");
		location.setLatitude(13.543296);
		location.setLatitude(100.924562);
		// Ads
		AdRequest.Builder adBuilder = new AdRequest.Builder();
		adBuilder.setLocation(location);

		AdRequest adRequest = adBuilder.build();
		AdView adView = (AdView) findViewById(R.id.adView);
		adView.loadAd(adRequest);
	}

}