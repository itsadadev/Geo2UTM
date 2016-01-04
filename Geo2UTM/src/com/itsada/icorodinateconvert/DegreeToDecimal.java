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
import com.itsada.geo2utm.core.DegreeGeoCoordinate;
import com.itsada.geo2utm.core.GeoCoordinate;

public class DegreeToDecimal extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.degree_to_decimal);

		setActionBar();

		textTitle.setText("Degree to Decimal");

		textTitle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		final EditText etLatDegree = (EditText) findViewById(R.id.etLatitudeDegree);
		final EditText etLatMinutes = (EditText) findViewById(R.id.etLatitudeMinutes);
		final EditText etLatSecond = (EditText) findViewById(R.id.etLatitudeSecond);

		final EditText etLonDegree = (EditText) findViewById(R.id.etLongitudeDegree);
		final EditText etLonMinutes = (EditText) findViewById(R.id.etLongitudeMinutes);
		final EditText etLonSecond = (EditText) findViewById(R.id.etLongitudeSecond);

		final TextView tvLat = (TextView) findViewById(R.id.tvLatitude);
		final TextView tvLon = (TextView) findViewById(R.id.tvLongitude);

		final TextView tvLatMessage = (TextView) findViewById(R.id.tvLatitudeMessage);
		final TextView tvLonMessage = (TextView) findViewById(R.id.tvLongitudeMessage);

		etLatDegree.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.equals(""))
					tvLatMessage.setVisibility(View.VISIBLE);
				else if (!s.equals("")
						&& !etLatMinutes.getText().toString().equals("")
						&& !etLatSecond.getText().toString().equals(""))
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

		etLatMinutes.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.equals(""))
					tvLatMessage.setVisibility(View.VISIBLE);
				else if (!s.equals("")
						&& !etLatDegree.getText().toString().equals("")
						&& !etLatSecond.getText().toString().equals(""))
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

		etLatSecond.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.equals(""))
					tvLatMessage.setVisibility(View.VISIBLE);
				else if (!s.equals("")
						&& !etLatDegree.getText().toString().equals("")
						&& !etLatMinutes.getText().toString().equals(""))
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

		etLonDegree.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.equals(""))
					tvLonMessage.setVisibility(View.VISIBLE);
				else if (!s.equals("")
						&& !etLonMinutes.getText().toString().equals("")
						&& !etLonSecond.getText().toString().equals(""))
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

		etLonMinutes.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.equals(""))
					tvLonMessage.setVisibility(View.VISIBLE);
				else if (!s.equals("")
						&& !etLonDegree.getText().toString().equals("")
						&& !etLonSecond.getText().toString().equals(""))
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

		etLonSecond.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.equals(""))
					tvLonMessage.setVisibility(View.VISIBLE);
				else if (!s.equals("")
						&& !etLonDegree.getText().toString().equals("")
						&& !etLonMinutes.getText().toString().equals(""))
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
				
				if (etLatDegree.getText().toString().equals("")
						|| etLatMinutes.getText().toString().equals("")
						|| etLatSecond.getText().toString().equals("")

						|| etLonDegree.getText().toString().equals("")
						|| etLonMinutes.getText().toString().equals("")
						|| etLonSecond.getText().toString().equals("")) {
					if (etLatDegree.getText().toString().equals("")
							|| etLatMinutes.getText().toString().equals("")
							|| etLatSecond.getText().toString().equals(""))
						tvLatMessage.setVisibility(View.VISIBLE);
					else
						tvLatMessage.setVisibility(View.GONE);
					if (etLonDegree.getText().toString().equals("")
							|| etLonMinutes.getText().toString().equals("")
							|| etLonSecond.getText().toString().equals(""))
						tvLonMessage.setVisibility(View.VISIBLE);
					else
						tvLonMessage.setVisibility(View.GONE);
				} else {

					GeoCoordinate geo = new DegreeGeoCoordinate(etLatDegree.getText()
							.toString(), etLatMinutes.getText().toString(),
							etLatSecond.getText().toString(), etLonDegree
									.getText().toString(), etLonMinutes
									.getText().toString(), etLonSecond
									.getText().toString());

					// double latDegree =
					// Double.parseDouble(etLatDegree.getText()
					// .toString());
					// double latMinutes = Double.parseDouble(etLatMinutes
					// .getText().toString());
					// double latSecond =
					// Double.parseDouble(etLatSecond.getText()
					// .toString());
					//
					// double lonDegree =
					// Double.parseDouble(etLonDegree.getText()
					// .toString());
					// double lonMinutes = Double.parseDouble(etLonMinutes
					// .getText().toString());
					// double lonSecond =
					// Double.parseDouble(etLonSecond.getText()
					// .toString());

					if (geo.validate()) {
						if (geo.latitudeIsValid()) {
							tvLatMessage
									.setText("The latitude is valid, please check.");
							tvLatMessage.setVisibility(View.VISIBLE);
						}
						if (geo.longitudeIsValid()) {
							tvLonMessage
									.setText("The longitude is valid, please check.");
							tvLonMessage.setVisibility(View.VISIBLE);
						}
					} else {
						tvLatMessage.setVisibility(View.GONE);
						tvLonMessage.setVisibility(View.GONE);
						tvLat.setText(String.valueOf(geo.getLatitude()));
						tvLon.setText(String.valueOf(geo.getLongitude()));
						// tvLat.setText(convertCoordinate.convertDegreeToDecimal(
						// latDegree, latMinutes, latSecond));
						// tvLon.setText(convertCoordinate.convertDegreeToDecimal(
						// lonDegree, lonMinutes, lonSecond));
					}
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