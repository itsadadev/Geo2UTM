package com.itsada.geo2utm.core;

import com.itsada.framework.cooridate.ConvertCoordinate;

public class GeoCoordinate extends Cooridinate {
	protected double latitude;
	protected double longitude;
	private Boolean latitudeisValide;
	private Boolean longitudeisValide;

	public GeoCoordinate(){}
	
	public GeoCoordinate(String lat, String lon) {
		this.latitude = Double.parseDouble(lat);
		this.longitude = Double.parseDouble(lon);
	}

	public GeoCoordinate(String latDegree, String latMinutes, String latSecond,
			String lonDegree, String lonMinutes, String lonSecond) {
		ConvertCoordinate convertCoordinate = new ConvertCoordinate();
		this.latitude = Double.parseDouble(convertCoordinate
				.convertDegreeToDecimal(Double.parseDouble(latDegree),
						Double.parseDouble(latMinutes),
						Double.parseDouble(latSecond)));
		this.longitude = Double.parseDouble(convertCoordinate
				.convertDegreeToDecimal(Double.parseDouble(lonDegree),
						Double.parseDouble(lonMinutes),
						Double.parseDouble(lonSecond)));
	}

	public GeoCoordinate(Double latDegree, Double latMinutes, Double latSecond,
			Double lonDegree, Double lonMinutes, Double lonSecond) {
		ConvertCoordinate convertCoordinate = new ConvertCoordinate();
		this.latitude = Double.parseDouble(convertCoordinate
				.convertDegreeToDecimal(latDegree, latMinutes, latSecond));
		this.longitude = Double.parseDouble(convertCoordinate
				.convertDegreeToDecimal(lonDegree, lonMinutes, lonSecond));
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public Boolean latitudeIsValid() {
		return latitudeisValide;
	}

	public Boolean longitudeIsValid() {
		return longitudeisValide;
	}

	@Override
	public Boolean validate() {
		this.latitudeisValide = this.latitude < -85 || this.latitude > 85;
		this.longitudeisValide = this.longitude < -180 || this.longitude > 180;
		return this.latitudeisValide || this.longitudeisValide;
	}
}
