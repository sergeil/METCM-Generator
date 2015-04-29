package org.lissovski.metcmgenerator.exporter;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.lissovski.metcmgenerator.generator.GeneratorOutput;

public class ReportExporter {
	private class FormatUtils {
		public String padRight(String input) {
			return String.format("%-6s", input);
		}
		
		public String padLeft(String input, Integer num, String with) {
			return String.format("%"+num+"s", input).replace("" , with);
		}
		
		public String padFloor(String input) {
			return padLeft(input, 2, "0");
		}
	}
	
	public void export(GeneratorOutput generatorOutput, String path) {
		StringWriter writer = new StringWriter();
		
		Properties velocityConfig = new Properties();
		velocityConfig.setProperty("resource.loader", "file");
		velocityConfig.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		velocityConfig.setProperty("file.resource.loader.path", "/Users/sergeil/Documents/workspaces/eclipse-rcp/METCM Generator/templates");
		
		VelocityEngine ve = new VelocityEngine();
		ve.init(velocityConfig);
		
		VelocityContext context = new VelocityContext();
		context.put("u", new FormatUtils());
		context.put("floors", generatorOutput.getFloors());
		context.put("gv", generatorOutput.getInput()); // ground values
		
		Template tpl = ve.getTemplate("report.vm");
		tpl.merge(context, writer);
		
		System.out.println(writer.toString());
	}
}
