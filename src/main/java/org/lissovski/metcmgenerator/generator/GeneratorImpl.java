package org.lissovski.metcmgenerator.generator;

import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class GeneratorImpl implements ReportGenerator {
	private int randInt(int min, int max) {
	    Random rand = new Random();
    	
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	private Double convertTemperatureToKelvins(Double temperatureInCelcius) {
		return (double) Math.round(temperatureInCelcius + 273.15) * 10;
	}
	
	private Integer convertFloorToMeters(Integer floor) {
		return floor * 300; // TODO
	}
	
	private Double calculateTemperature(Double groundTemp, Integer altitudeInMeters) {
		return groundTemp - (altitudeInMeters * 0.006);
	}
	
	private Double calculateAirPressure(Double groundAirPressure, Integer altitude) {
		return (double) Math.round(groundAirPressure - altitude / 10.5);
	}
	
	public GeneratorOutput generate(GeneratorInput input) {
		List<Floor> floors = new Vector<Floor>();
		
		floors.add(
			new Floor(0, input.getWindDirection(), input.getWindSpeed(), convertTemperatureToKelvins(input.getTemperature()), input.getAirPressure())
		);
		
		for (int i=1; i<input.getFloorsCount(); i++) {
			Integer altitude = convertFloorToMeters(i);
			Floor previousFloor = floors.get(i-1);
			
			Integer floor = i;
			Double windDirection = new Double(randInt(0, 360));
			Double windSpeed = previousFloor.getWindSpeed() + randInt(0, 10);
			Double temperatureInKelvins = convertTemperatureToKelvins(calculateTemperature(input.getTemperature(), altitude));
			Double airPressure = calculateAirPressure(input.getAirPressure(), altitude);
			
			floors.add(new Floor(floor, windDirection, windSpeed, temperatureInKelvins, airPressure));			
		}
		
		return new GeneratorOutput(input, floors);
	}
}
