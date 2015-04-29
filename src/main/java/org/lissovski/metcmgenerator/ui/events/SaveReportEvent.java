package org.lissovski.metcmgenerator.ui.events;

import java.util.EventObject;

import org.lissovski.metcmgenerator.ui.SaveReportShellValues;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class SaveReportEvent extends EventObject {
	private static final long serialVersionUID = -4577307846272312194L;

	private SaveReportShellValues saveReportShellValues;

	public SaveReportEvent(Object source, SaveReportShellValues saveReportShellValues) {
		super(source);
		
		this.saveReportShellValues = saveReportShellValues;
	}

	public SaveReportShellValues getSaveReportShellValues() {
		return saveReportShellValues;
	}
}
