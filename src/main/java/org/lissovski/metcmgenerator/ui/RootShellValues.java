package org.lissovski.metcmgenerator.ui;

import java.util.Date;

import org.lissovski.metcmgenerator.generator.GeneratorInput;

public class RootShellValues {
	private String octant ="";
	private String location = "";
	private String windSpeed = "";
	private String windDirection = "";
	private String airPressure = "";
	private Date date = new Date();
	private Date time = new Date();
	private String floorsCount = "31";
	
	public RootShellValues(String octant, String location, String windSpeed,
			String windDirection, String airPressure, Date date, Date time,
			String floorsCount) {
		
		this.octant = octant;
		this.location = location;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.airPressure = airPressure;
		this.date = date;
		this.time = time;
		this.floorsCount = floorsCount;
	}

	public GeneratorInput createGeneratorInput() {
		return new GeneratorInput(
			Integer.parseInt(octant),
			Integer.parseInt(location), 
			Double.parseDouble(windSpeed), 
			Double.parseDouble(windDirection), 
			Double.parseDouble(airPressure),
			null, // TODO
			Integer.parseInt(floorsCount)
		);
	}

	public String getOctant() {
		return octant;
	}

	public void setOctant(String octant) {
		this.octant = octant;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getAirPressure() {
		return airPressure;
	}

	public void setAirPressure(String airPressure) {
		this.airPressure = airPressure;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFloorsCount() {
		return floorsCount;
	}

	public void setFloorsCount(String floorsCount) {
		this.floorsCount = floorsCount;
	}

	// private
	
	public RootShellValues() {
	}	
}
