package org.lissovski.metcmgenerator.generator;

import java.util.Date;

public class GeneratorInput {
	private Integer octant;
	private Integer location;
	private Double windSpeed;
	private Double windDirection;
	private Double airPressure;
	private Date dateTime;
	private Integer floorsCount;

	public GeneratorInput(Integer octant, Integer location, Double windSpeed,
			Double windDirection, Double airPressure, Date dateTime,
			Integer floorsCount) {
		super();
		
		this.octant = octant;
		this.location = location;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.airPressure = airPressure;
		this.dateTime = dateTime;
		this.floorsCount = floorsCount;
	}

	public Integer getOctant() {
		return octant;
	}

	public Integer getLocation() {
		return location;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public Double getWindDirection() {
		return windDirection;
	}

	public Double getAirPressure() {
		return airPressure;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public Integer getFloorsCount() {
		return floorsCount;
	}

	@Override
	public String toString() {
		return "GeneratorInput [octant=" + octant + ", location=" + location
				+ ", windSpeed=" + windSpeed + ", windDirection="
				+ windDirection + ", airPressure=" + airPressure
				+ ", dateTime=" + dateTime + ", floorsCount=" + floorsCount
				+ "]";
	}

	// private:
	
	public GeneratorInput() {
	}

	public void setOctant(Integer octant) {
		this.octant = octant;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public void setWindDirection(Double windDirection) {
		this.windDirection = windDirection;
	}

	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public void setFloorsCount(Integer floorsCount) {
		this.floorsCount = floorsCount;
	}
}
