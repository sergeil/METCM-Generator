package org.lissovski.metcmgenerator.ui;

import java.util.Date;

import org.lissovski.metcmgenerator.generator.GeneratorInput;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class RootShellValues {
	private String octant ="";
	private String location = "";
	private String windSpeed = "";
	private String windDirection = "";
	private String temperature = "";
	private String airPressure = "";
	private Date date = new Date();
	private Date time = new Date();
	private String floorsCount = "31";
	
	public RootShellValues(String octant, String location, String windSpeed,
			String windDirection, String temperature, String airPressure, Date date, 
			Date time, String floorsCount) {
		
		this.octant = octant;
		this.location = location;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.temperature = temperature;
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
			Double.parseDouble(temperature),
			Double.parseDouble(airPressure),
			null, // TODO
			Integer.parseInt(floorsCount)
		);
	}
	
	public String getOctant() {
		return octant;
	}

	public String getLocation() {
		return location;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getAirPressure() {
		return airPressure;
	}

	public Date getDate() {
		return date;
	}

	public Date getTime() {
		return time;
	}

	public String getFloorsCount() {
		return floorsCount;
	}
	
	// private:

	public RootShellValues() {
	}

	public void setOctant(String octant) {
		this.octant = octant;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public void setAirPressure(String airPressure) {
		this.airPressure = airPressure;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setFloorsCount(String floorsCount) {
		this.floorsCount = floorsCount;
	}
}
