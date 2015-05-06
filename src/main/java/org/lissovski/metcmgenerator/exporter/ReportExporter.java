package org.lissovski.metcmgenerator.exporter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import org.lissovski.metcmgenerator.generator.Floor;
import org.lissovski.metcmgenerator.generator.GeneratorInput;
import org.lissovski.metcmgenerator.generator.GeneratorOutput;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class ReportExporter {
	private final static Logger logger = Logger.getLogger(ReportExporter.class.getName());
	
	public String padLeft(String input, Integer num, String with) {
		return String.format("%"+num+"s", input).replace(" ", with);
	}
	
	protected void writeToFile(String path, String contents) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"))) {
		   writer.write(contents);
		}
	}
	
	protected String createFilename() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyy-HHmm");
		
		return "ilmateade-" + dateFormatter.format(new Date()) + ".txt";
	}
	
	protected void prettyPrintIfNeeded(StringBuilder output, boolean prettyPrint) {
		if (prettyPrint) {
			output.append(" ");
		}
	}
 	
	public void export(GeneratorOutput generatorOutput, String path, boolean prettyPrint) {
		GeneratorInput input = generatorOutput.getInput();
		
		Calendar c = Calendar.getInstance();
		StringBuilder output = new StringBuilder();
		
		String newLineSeq = "\r\n";
		
		// header
		output.append("METCM" + input.getOctant());
		output.append(" ");
		output.append(Integer.toString(input.getLocation()));
		output.append(" ");
		output.append(padLeft(Integer.toString(c.get(Calendar.DAY_OF_MONTH)), 2, "0"));
		output.append(padLeft(Integer.toString(c.get(Calendar.HOUR_OF_DAY)), 2, "0"));
		output.append(Integer.toString(c.get(Calendar.MINUTE)).substring(0, 1));
		output.append("0"); // duration
		
		output.append(" ");
		
		output.append("010");
		output.append(Integer.toString(input.getAirPressure().intValue()).substring(1));
		
		output.append(newLineSeq);
		
		for (Floor floor : generatorOutput.getFloors()) {
			output.append(padLeft(Integer.toString(floor.getFloor()), 2, "0")); // floor
			prettyPrintIfNeeded(output, prettyPrint);
			output.append(padLeft(Integer.toString(floor.getWindDirection().intValue()), 3, "0")); // wind direction
			prettyPrintIfNeeded(output, prettyPrint);
			output.append(padLeft(Integer.toString(floor.getWindSpeed().intValue()), 3, "0")); // wind speed
			output.append(prettyPrint ? "  " : " ");
			output.append(padLeft(Integer.toString(floor.getTemperature().intValue()), 4, "0"));
			prettyPrintIfNeeded(output, prettyPrint);
			output.append(padLeft(Integer.toString(floor.getAirPressure().intValue()), 4, "0"));

			// last floor must not have a new line
			if ((input.getFloorsCount()-1) != floor.getFloor()) {
				output.append(newLineSeq); // next row
			}
		}
		
		try {
			path += "/" + createFilename();
			
			writeToFile(path, output.toString());
			
			logger.info("A weather report has been successfully saved to " + path);
		} catch (Exception e) {
			logger.severe("Unable to save a report to a file '" + path + "', error: " + e.getMessage());
		}
	}
}
