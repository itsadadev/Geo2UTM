package com.itsada.icorodinateconvert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.itsada.icorodinateconvert.R;

public class Dashboard extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.dashboard);

		setActionBar();

		findViewById(R.id.btDcToDmc).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getApplicationContext(),
						DecimalToDegree.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.btDmcToDc).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getApplicationContext(),
						DegreeToDecimal.class);
				startActivity(intent);
			}
		});
		
		findViewById(R.id.btGeoToUtm).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getApplicationContext(),
						GeoToUTM.class);
				startActivity(intent);
			}
		});
		
		findViewById(R.id.btUtmToGeo).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getApplicationContext(),
						UTMToGeo.class);
				startActivity(intent);
			}
		});
		
		findViewById(R.id.btMap).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getApplicationContext(),
						MapActivity.class);
				startActivity(intent);
			}
		});
	}

}
