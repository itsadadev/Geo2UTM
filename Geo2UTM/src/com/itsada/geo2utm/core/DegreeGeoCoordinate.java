package com.itsada.geo2utm.core;

public class DegreeGeoCoordinate extends GeoCoordinate {

	private double latDegree;
	private double latMinutes;
	private double latSecond;

	private double lonDegree;
	private double lonMinutes;
	private double lonSecond;

	public DegreeGeoCoordinate(String latDegree, String latMinutes,
			String latSecond, String lonDegree, String lonMinutes,
			String lonSecond) {
		
		super(latDegree, latMinutes, latSecond, lonDegree, lonMinutes,
				lonSecond);

		this.latDegree = Double.parseDouble(latDegree);
		this.latMinutes = Double.parseDouble(latMinutes);
		this.latSecond = Double.parseDouble(latSecond);

		this.lonDegree = Double.parseDouble(lonDegree);
		this.lonMinutes = Double.parseDouble(lonMinutes);
		this.lonSecond = Double.parseDouble(lonSecond);
	}
	
    @Override
    public Boolean validate() {
    	Boolean isValide = this.latMinutes > 59 || this.latSecond > 59
    			|| this.lonMinutes > 59 || this.lonSecond > 59;
    	return super.validate() || isValide;
    }

	public double getLatDegree() {
		return latDegree;
	}

	public double getLatMinutes() {
		return latMinutes;
	}

	public double getLatSecond() {
		return latSecond;
	}

	public double getLonDegree() {
		return lonDegree;
	}

	public double getLonMinutes() {
		return lonMinutes;
	}

	public double getLonSecond() {
		return lonSecond;
	}
}
