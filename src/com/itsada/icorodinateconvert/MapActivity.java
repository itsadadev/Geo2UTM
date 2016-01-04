package com.itsada.icorodinateconvert;

import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.itsada.framework.cooridate.CoordinateConversion;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

@SuppressLint("InflateParams")
public class MapActivity extends FragmentActivity implements
		OnMapReadyCallback, LocationListener {

	private GoogleMap mMap;
	private Marker mMarker;
	private LocationManager lm;
	private double lat, lon;
	private DecimalFormat df = new DecimalFormat("#.####");
	private boolean isUtm = false;
	private LatLng coordinate;
	private SlidingMenu slidingMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.map_layout);

		ActionBar actionBar = (ActionBar) getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);

		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(R.layout.custom_map_actionbar,
				null);

		final Button btGeo = (Button) mCustomView.findViewById(R.id.btGeo);
		final Button btUTM = (Button) mCustomView.findViewById(R.id.btUTM);
		ImageView imgMenu = (ImageView) mCustomView.findViewById(R.id.menu);

		btGeo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				btGeo.setBackgroundResource(R.drawable.troggle_on_active_shape);
				btUTM.setBackgroundResource(R.drawable.troggle_off_normal_shape);
				isUtm = false;
				drawMap();
			}
		});

		btUTM.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btGeo.setBackgroundResource(R.drawable.troggle_on_normal_shape);
				btUTM.setBackgroundResource(R.drawable.troggle_off_active_shape);
				isUtm = true;
				drawMap();
			}
		});

		imgMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				slideMenu();
			}
		});

		actionBar.setCustomView(mCustomView);
		actionBar.setDisplayShowCustomEnabled(true);

		mMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		if (mMap != null) {
			mMap.getUiSettings().setZoomControlsEnabled(true);

			// Enabling MyLocation Layer of Google Map
			mMap.setMyLocationEnabled(true);
		}

		// Getting LocationManager object from System Service LOCATION_SERVICE
		// lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		// lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5,
		// this);

		// add overlay
		// drawMap();
	}

	protected void slideMenu() {

		if (slidingMenu == null) {
			slidingMenu = new SlidingMenu(this);

			slidingMenu.setShadowWidth(5);
			slidingMenu.setFadeDegree(0.0f);
			slidingMenu.setMode(SlidingMenu.LEFT);
			slidingMenu.setBehindWidth(100);
			slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
			slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			slidingMenu.setMenu(R.layout.menu_frame);
			slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);

		} else {
			if (slidingMenu.isMenuShowing()) {
				slidingMenu.showContent(true);
				slidingMenu.showMenu(false);
			} else {
				slidingMenu.showContent(true);
				slidingMenu.showMenu(false);
			}
		}
	}

	public void onResume() {
		super.onResume();

		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		boolean isNetwork = lm
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		boolean isGPS = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if (isNetwork) {
			lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000,
					10, this);
			Location loc = lm
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if (loc != null) {
				lat = loc.getLatitude();
				lon = loc.getLongitude();
			}
		}

		if (isGPS) {
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10,
					this);
			Location loc = lm
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (loc != null) {
				lat = loc.getLatitude();
				lon = loc.getLongitude();
			}
		}
	}

	public void onPause() {
		super.onPause();
		lm.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		coordinate = new LatLng(location.getLatitude(), location.getLongitude());
		lat = location.getLatitude();
		lon = location.getLongitude();
		//
		drawMap();
	}

	private void drawMap() {

		if (mMarker != null)
			mMarker.remove();

		StringBuilder builder = new StringBuilder();

		if (isUtm) {
			CoordinateConversion conversion = new CoordinateConversion();
			CoordinateConversion.LatLon2UTM latLonToUTM = conversion
					.latLonToUTM(lat, lon);
			int e = (int) latLonToUTM.easting;
			int n = (int) latLonToUTM.northing;

			builder.append(String.valueOf(latLonToUTM.zone) + " ");
			builder.append(String.valueOf(e).subSequence(0, 3));
			builder.append(String.valueOf(n).subSequence(0, 3));

			mMarker = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(lat, lon)).title("UTM")
					.snippet(builder.toString()));
		} else {
			builder.append(df.format(lat));
			builder.append(" , " + df.format(lon));
			mMarker = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(lat, lon)).title("Geo Graphic")
					.snippet(builder.toString()));
		}

		BitmapDescriptor image = BitmapDescriptorFactory
				.fromResource(R.drawable.ic_launcher);
		GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions();
		groundOverlayOptions.image(image).position(coordinate, 20);

		mMarker.showInfoWindow();
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 16));
		mMap.addGroundOverlay(groundOverlayOptions);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapReady(GoogleMap arg0) {
		// TODO Auto-generated method stub

	}

}
