package org.lissovski.metcmgenerator.ui.events;

import java.util.EventObject;
import java.util.List;
import java.util.Vector;

import org.lissovski.metcmgenerator.generator.GeneratorInput;
import org.lissovski.metcmgenerator.generator.Floor;

public class ExportReportEvent extends EventObject {
	private static final long serialVersionUID = 7888191502333315597L;
	
	private GeneratorInput generatorConfigurationData;
	private List<Floor> reportRows = new Vector<Floor>();
	
	public ExportReportEvent(Object source,
			GeneratorInput generatorConfigurationData,
			List<Floor> reportRows) {
		super(source);
		
		this.generatorConfigurationData = generatorConfigurationData;
		this.reportRows = reportRows;
	}

	public GeneratorInput getGeneratorConfigurationData() {
		return generatorConfigurationData;
	}

	public List<Floor> getReportRows() {
		return reportRows;
	}
}
