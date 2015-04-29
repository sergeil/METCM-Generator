package org.lissovski.metcmgenerator.generator;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class GeneratorImpl implements ReportGenerator {
	private int randInt(int min, int max) {
	    Random rand = new Random();
    	
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public GeneratorOutput generate(GeneratorInput generatorInput) {
		List<Floor> floors = new Vector<Floor>();
		
		for (int i=0; i<generatorInput.getFloorsCount(); i++) {
			floors.add(
				new Floor(i, new Double(randInt(0, 360)), 2.0, 5.0, new Double(990 + randInt(0, 20)))
			);			
		}
		
		return new GeneratorOutput(generatorInput, floors);
	}
}
