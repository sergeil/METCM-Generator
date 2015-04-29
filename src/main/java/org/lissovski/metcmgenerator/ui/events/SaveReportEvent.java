package org.lissovski.metcmgenerator.ui.events;

import java.util.EventObject;

import org.lissovski.metcmgenerator.ui.SaveReportConfig;

public class SaveReportEvent extends EventObject {
	private static final long serialVersionUID = -4577307846272312194L;

	private SaveReportConfig saveReportConfig;

	public SaveReportEvent(Object source, SaveReportConfig saveReportConfig) {
		super(source);
		
		this.saveReportConfig = saveReportConfig;
	}

	public SaveReportConfig getSaveReportConfig() {
		return saveReportConfig;
	}
}
