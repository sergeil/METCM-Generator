package org.lissovski.metcmgenerator.generator;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratorImplTest {
    @Test
    public void howWellReportsAreGenerated() {
        GeneratorInput input = new GeneratorInput();
        
        input.setAirPressure(1024.0);
        input.setDateTime(new Date());
        input.setFloorsCount(2);
        input.setLocation(123456);
        input.setOctant(3);
        input.setTemperature(10.0);
        input.setWindDirection(60.0);
        input.setWindSpeed(5.0);
        
        GeneratorImpl g = new GeneratorImpl();
        GeneratorOutput output = g.generate(input);
        
        assertNotNull(output);
        
        List<Floor> floors = output.getFloors();
        
        assertEquals(2, floors.size());
        
        Floor groundFloor = floors.get(0);
        Floor firstFloor = floors.get(1);
        
        assertEquals(groundFloor.getAirPressure(), input.getAirPressure());
        assertTrue(groundFloor.getTemperature() > input.getTemperature());
        assertEquals(groundFloor.getWindDirection(), input.getWindDirection());
        assertEquals(groundFloor.getWindSpeed(), input.getWindSpeed());
        assertEquals(0, (int)groundFloor.getFloor());
        
        assertTrue(firstFloor.getAirPressure() <= groundFloor.getAirPressure());
        assertTrue(firstFloor.getTemperature() <= groundFloor.getTemperature());
        assertTrue(firstFloor.getWindSpeed() >= groundFloor.getWindSpeed());
        assertEquals(1, (int)firstFloor.getFloor());
    }
}
