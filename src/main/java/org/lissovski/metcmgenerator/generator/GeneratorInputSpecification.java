package org.lissovski.metcmgenerator.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.lissovski.metcmgenerator.ui.RootShellValues;

public class GeneratorInputSpecification {
	private static class Field {
		public String label;
		public String value;
		
		public Field(String label, String value) {
			this.label = label;
			this.value = value;
		}
	}
	
	public static List<String> validate(RootShellValues input) {
		List<String> errors = new Vector<String>();
		List<String> emptyFields = new Vector<String>();
		
		Map<String, Field> values = new HashMap<String, Field>();
		values.put("octant", new Field("Octant", input.getOctant()));
		values.put("location", new Field("Location", input.getLocation()));
		values.put("windSpeed", new Field("Wind speed", input.getWindSpeed()));
		values.put("windDirection", new Field("Wind direction", input.getWindDirection()));
		values.put("airPressure", new Field("Air pressure", input.getAirPressure()));
		values.put("floorsCount", new Field("Floors count", input.getFloorsCount()));
		
		for (Map.Entry<String, Field> entry : values.entrySet()) {
		    if (entry.getValue().value.replaceAll(" ", "").equals("")) {
		    	errors.add(entry.getValue().label + "' must be provided");
		    	
		    	emptyFields.add(entry.getKey());
		    }
		}
		
		if (!emptyFields.contains("octant")) {
			Integer octant = Integer.parseInt(values.get("octant").value);
			
			if (octant < 0 || octant > 8) {
				errors.add("Octant must be between 0 and 8");
			}
		}
		
		return errors;
	}
}
