package com.itsada.geo2utm.core;

import com.itsada.framework.cooridate.ConvertCoordinate;

public class DecimalGeoCoordinate extends GeoCoordinate {

	public DecimalGeoCoordinate(String lat, String lon) {
		super(lat, lon);
		if (validate()) {
			ConvertCoordinate convertCoordinate = new ConvertCoordinate();
			this.latitude = Double.parseDouble(convertCoordinate
					.convertDecimalToDegree(this.latitude));
			this.longitude = Double.parseDouble(convertCoordinate
					.convertDecimalToDegree(this.longitude));
		}
	}
}
