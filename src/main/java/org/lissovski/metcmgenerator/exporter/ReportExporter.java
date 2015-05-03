package org.lissovski.metcmgenerator.exporter;

import java.util.Date;

import org.lissovski.metcmgenerator.generator.Floor;
import org.lissovski.metcmgenerator.generator.GeneratorInput;
import org.lissovski.metcmgenerator.generator.GeneratorOutput;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class ReportExporter {
	private class FormatUtils {
		public String padLeft(String input, Integer num, String with) {
			return String.format("%"+num+"s", input).replace(" ", with);
		}
	}
	
	public void export(GeneratorOutput generatorOutput, String path) {
		FormatUtils utils = new FormatUtils();
		
		GeneratorInput input = generatorOutput.getInput();
		Date date = input.getDateTime();
		
		String weatherStationLocation = "567894";
		StringBuilder output = new StringBuilder();
		
		// header
		output.append("METCM" + input.getOctant() + " " + weatherStationLocation);
		output.append(" ");
		output.append(utils.padLeft(Integer.toString(date.getDate()), 2, "0"));
		output.append(utils.padLeft(Integer.toString(date.getHours()), 2, "0"));
		output.append(Integer.toString(date.getMinutes()).substring(0, 1));
		output.append("0"); // duration
		
		output.append(" ");
		
		output.append("010");
		output.append(Integer.toString(input.getAirPressure().intValue()).substring(1));
		
		output.append("\n");
		
		for (Floor floor : generatorOutput.getFloors()) {
			output.append(utils.padLeft(Integer.toString(floor.getFloor()), 2, "0")); // floor
			output.append(utils.padLeft(Integer.toString(floor.getWindDirection().intValue()), 3, "0")); // wind direction
			output.append(utils.padLeft(Integer.toString(floor.getWindSpeed().intValue()), 3, "0")); // wind speed
			output.append(" ");
			output.append(utils.padLeft(Integer.toString(floor.getTemperature().intValue()), 4, "0"));
			output.append(utils.padLeft(Integer.toString(floor.getAirPressure().intValue()), 4, "0"));
			
			output.append("\n"); // next row
		}
		
		System.out.println(output.toString());
	}
}
