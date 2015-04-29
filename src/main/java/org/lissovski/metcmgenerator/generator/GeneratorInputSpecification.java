package org.lissovski.metcmgenerator.generator;

import java.util.List;
import java.util.Vector;

public class GeneratorInputSpecification {
	public static List<String> validate(GeneratorInput input) {
		List<String> errors = new Vector<String>();
		
		if (input.getOctant() == null) {
			errors.add("Octant is not specified");
		} else if (input.getOctant() < 0 || input.getOctant() > 8) {
			errors.add("Octant should be between 0 and 8");
		}
		
		return errors;
	}
}
