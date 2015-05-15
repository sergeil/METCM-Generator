package org.lissovski.metcmgenerator.generator;

import static org.junit.Assert.*;

import org.junit.Test;

public class FloorAltitudeResolverTest {
	@Test
	public void testValidFloors() {
		FloorAltitudeResolver far = new FloorAltitudeResolver();
		
		int startFloor = 1;
		int endFloor = 26;
		
		int previous = 0;
		for (int i=startFloor; i<endFloor; i++) {
			int current = far.resolve(i);
			
			if (previous > current) {
				fail("Every next floor's attitude must be higher than a previous one");
			}
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWhenNegativeFloorGiven() {
		FloorAltitudeResolver far = new FloorAltitudeResolver();
		
		far.resolve(-1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testWhenOutOfLimitFloorIsGiven() {
		FloorAltitudeResolver far = new FloorAltitudeResolver();
		
		far.resolve(27);
	}
}
