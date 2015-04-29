package org.lissovski.metcmgenerator.ui.events;

import java.util.EventObject;

import org.lissovski.metcmgenerator.generator.GeneratorInput;

public class GenerateReportEvent extends EventObject {
	private static final long serialVersionUID = 4859529483681982074L;
	
	private GeneratorInput generatorInput;
	
	public GenerateReportEvent(Object source, GeneratorInput generatorInput) {
		super(source);
		
		this.generatorInput = generatorInput;
	}

	public GeneratorInput getGeneratorInput() {
		return generatorInput;
	}

	@Override
	public String toString() {
		return generatorInput.toString();
	}
}
