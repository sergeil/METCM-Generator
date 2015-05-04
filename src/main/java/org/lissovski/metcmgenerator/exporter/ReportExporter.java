package org.lissovski.metcmgenerator.exporter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;

import org.lissovski.metcmgenerator.generator.Floor;
import org.lissovski.metcmgenerator.generator.GeneratorInput;
import org.lissovski.metcmgenerator.generator.GeneratorOutput;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class ReportExporter {
	public String padLeft(String input, Integer num, String with) {
		return String.format("%"+num+"s", input).replace(" ", with);
	}
	
	protected void writeToFile(String path, String contents) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"))) {
		   writer.write(contents);
		}
	}
	
	protected String createFilename() {
		Calendar c = Calendar.getInstance();
		
		StringBuilder r = new StringBuilder();
		
		r.append("ilmateade-");
		r.append(padLeft(Integer.toString(c.get(Calendar.DAY_OF_MONTH)), 2, "0"));
		r.append(padLeft(Integer.toString(c.get(Calendar.MONTH) + 1), 2, "0"));
		r.append(c.get(Calendar.YEAR));
		r.append("-");
		r.append(padLeft(Integer.toString(c.get(Calendar.HOUR_OF_DAY)), 2, "0"));
		r.append(padLeft(Integer.toString(c.get(Calendar.MINUTE)), 2, "0"));
		r.append(".txt");
		
		return r.toString();
	}
 	
	public void export(GeneratorOutput generatorOutput, String path) {
		GeneratorInput input = generatorOutput.getInput();
		Date date = input.getDateTime();
		
		String weatherStationLocation = "567894";
		StringBuilder output = new StringBuilder();
		
		// header
		output.append("METCM" + input.getOctant() + " " + weatherStationLocation);
		output.append(" ");
		output.append(padLeft(Integer.toString(date.getDate()), 2, "0"));
		output.append(padLeft(Integer.toString(date.getHours()), 2, "0"));
		output.append(Integer.toString(date.getMinutes()).substring(0, 1));
		output.append("0"); // duration
		
		output.append(" ");
		
		output.append("010");
		output.append(Integer.toString(input.getAirPressure().intValue()).substring(1));
		
		output.append("\n");
		
		for (Floor floor : generatorOutput.getFloors()) {
			output.append(padLeft(Integer.toString(floor.getFloor()), 2, "0")); // floor
			output.append(padLeft(Integer.toString(floor.getWindDirection().intValue()), 3, "0")); // wind direction
			output.append(padLeft(Integer.toString(floor.getWindSpeed().intValue()), 3, "0")); // wind speed
			output.append(" ");
			output.append(padLeft(Integer.toString(floor.getTemperature().intValue()), 4, "0"));
			output.append(padLeft(Integer.toString(floor.getAirPressure().intValue()), 4, "0"));
			
			output.append("\n"); // next row
		}
		
		try {
			path += "/" + createFilename();
			
			System.out.println(path);
			
			writeToFile(path, output.toString());
		} catch (Exception e) {
			e.printStackTrace(); // TODO
		}
	}
}
