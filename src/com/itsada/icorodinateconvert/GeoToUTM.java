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
import com.itsada.framework.cooridate.CoordinateConversion;
import com.itsada.icorodinateconvert.R;

public class GeoToUTM extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.geo_to_utm);

		setActionBar();

		textTitle.setText("Geo to UTM");

		textTitle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		final EditText etLat = (EditText) findViewById(R.id.etLatitude);
		final EditText etLon = (EditText) findViewById(R.id.etLongitude);

		final TextView tvEasting = (TextView) findViewById(R.id.tvEasting);
		final TextView tvNorthing = (TextView) findViewById(R.id.tvNorthing);
		final TextView tvZone = (TextView) findViewById(R.id.tvZone);

		final TextView tvLatMessage = (TextView) findViewById(R.id.tvLatitudeMessage);
		final TextView tvLonMessage = (TextView) findViewById(R.id.tvLongitudeMessage);

		etLat.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.equals("")) {
					tvLatMessage.setText("Please enter latitude");
					tvLatMessage.setVisibility(View.VISIBLE);
				} else if (Double.parseDouble(s.toString()) > 90
						|| Double.parseDouble(s.toString()) < -90) {
					tvLatMessage
							.setText("Please enter latitude interval -90 to 90");
					tvLatMessage.setVisibility(View.VISIBLE);
				} else
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

				if (s.equals("")) {
					tvLonMessage.setText("Please enter longitude");
					tvLonMessage.setVisibility(View.VISIBLE);
				} else if (Double.parseDouble(s.toString()) > 180
						|| Double.parseDouble(s.toString()) < -180) {

					tvLonMessage
							.setText("Please enter longitude interval -180 to 180");
					tvLonMessage.setVisibility(View.VISIBLE);
				} else
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

				CoordinateConversion conversion = new CoordinateConversion();

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

					double lat = Double.parseDouble(etLat.getText().toString());
					double lon = Double.parseDouble(etLon.getText().toString());
					tvLatMessage.setVisibility(View.GONE);
					tvLonMessage.setVisibility(View.GONE);

					if (lat <= 90 && lat >= -90 && lon <= 180 && lon >= -180) {

						CoordinateConversion.LatLon2UTM latLonToUTM = conversion
								.latLonToUTM(lat, lon);

						tvEasting.setText(df.format(latLonToUTM.easting));
						tvNorthing.setText(df.format(latLonToUTM.northing));
						tvZone.setText(latLonToUTM.zone);
					} else {
						if (lat > 90 || lat < -90) {
							tvLatMessage
									.setText("Please enter latitude interval -90 to 90");
							tvLatMessage.setVisibility(View.VISIBLE);
						}

						if (lon > 180 || lon < -180) {
							tvLonMessage
									.setText("Please enter longitude interval -180 to 180");
							tvLonMessage.setVisibility(View.VISIBLE);
						}
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