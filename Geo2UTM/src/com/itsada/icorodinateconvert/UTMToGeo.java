package com.itsada.icorodinateconvert;

import android.annotation.SuppressLint;
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
import com.itsada.framework.cooridate.CoordinateConversion;
import com.itsada.icorodinateconvert.R;

@SuppressLint("DefaultLocale")
public class UTMToGeo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.utm_to_geo);

		setActionBar();

		textTitle.setText("UTM to Geo");

		textTitle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		final EditText etEasting = (EditText) findViewById(R.id.etEasting);
		final EditText etNorthing = (EditText) findViewById(R.id.etNorthing);
		final EditText etZone = (EditText) findViewById(R.id.etZone);
		final EditText etZoneString = (EditText) findViewById(R.id.etZoneString);

		final TextView tvLatitude = (TextView) findViewById(R.id.tvlatitude);
		final TextView tvLongitude = (TextView) findViewById(R.id.tvlongitude);

		final TextView tvLatMessage = (TextView) findViewById(R.id.tvEastingMessage);
		final TextView tvLonMessage = (TextView) findViewById(R.id.tvNorthingMessage);
		final TextView tvZoneMessage = (TextView) findViewById(R.id.tvZoneMessage);

		etEasting.addTextChangedListener(new TextWatcher() {

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

		etNorthing.addTextChangedListener(new TextWatcher() {

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

		etZone.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.equals(""))
					tvZoneMessage.setVisibility(View.VISIBLE);
				else if (s.equals("")
						&& etZoneString.getText().toString().equals(""))
					tvZoneMessage.setVisibility(View.GONE);
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

		etZoneString.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.equals(""))
					tvZoneMessage.setVisibility(View.VISIBLE);
				else if (s.equals("") && etZone.getText().toString().equals(""))
					tvZoneMessage.setVisibility(View.GONE);
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

				CoordinateConversion conversion = new CoordinateConversion();
				StringBuilder builder = new StringBuilder();
				builder.append(etZone.getText().toString());
				builder.append(" ");
				builder.append(etZoneString.getText().toString().toUpperCase());
				builder.append(" ");
				builder.append(etEasting.getText().toString());
				builder.append(" ");
				builder.append(etNorthing.getText().toString());

				// tvLat.setText(convertCoordinate.convertDecimalToDegree(45.3470));
				if (etEasting.getText().toString().equals("")
						|| etNorthing.getText().toString().equals("")
						|| etZone.getText().toString().equals("")
						|| etZoneString.getText().toString().equals("")) {
					if (etEasting.getText().toString().equals(""))
						tvLatMessage.setVisibility(View.VISIBLE);
					else
						tvLatMessage.setVisibility(View.GONE);
					if (etNorthing.getText().toString().equals(""))
						tvLonMessage.setVisibility(View.VISIBLE);
					else
						tvLonMessage.setVisibility(View.GONE);
					if (etZone.getText().toString().equals("")
							|| etZoneString.getText().toString().equals(""))
						tvZoneMessage.setVisibility(View.VISIBLE);
					else
						tvZoneMessage.setVisibility(View.GONE);
				} else {
					tvLatMessage.setVisibility(View.GONE);
					tvLonMessage.setVisibility(View.GONE);

					double[] latlon = conversion.utm2LatLon(builder.toString());

					tvLatitude.setText(df.format(latlon[0]));
					tvLongitude.setText(df.format(latlon[1]));
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