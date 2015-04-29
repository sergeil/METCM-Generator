package org.lissovski.metcmgenerator.ui.events;

import java.util.EventObject;
import java.util.List;
import java.util.Vector;

import org.lissovski.metcmgenerator.generator.Floor;
import org.lissovski.metcmgenerator.ui.RootShellValues;

public class ExportReportEvent extends EventObject {
	private static final long serialVersionUID = 7888191502333315597L;
	
	private RootShellValues rootShellValues;
	private List<Floor> reportRows = new Vector<Floor>();
	
	public ExportReportEvent(Object source,
			RootShellValues rootShellValues,
			List<Floor> reportRows) {
		super(source);
		
		this.rootShellValues = rootShellValues;
		this.reportRows = reportRows;
	}

	public RootShellValues getGeneratorConfigurationData() {
		return rootShellValues;
	}

	public List<Floor> getReportRows() {
		return reportRows;
	}
}
