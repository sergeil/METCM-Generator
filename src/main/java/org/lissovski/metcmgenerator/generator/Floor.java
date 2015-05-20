package org.lissovski.metcmgenerator.generator;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class Floor {
    private Integer floor;
    private Double windDirection;
    private Double windSpeed;
    private Double temperature;
    private Double airPressure;
    
    public Floor(Integer floor, Double windDirection, Double windSpeed,
            Double temperature, Double airPressure) {
        super();
        
        this.floor = floor;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.airPressure = airPressure;
    }

    public Integer getFloor() {
        return floor;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getAirPressure() {
        return airPressure;
    }
}
