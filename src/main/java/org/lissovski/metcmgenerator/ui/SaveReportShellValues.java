package org.lissovski.metcmgenerator.ui;

public class SaveReportShellValues {
	private String directoryPath;

	public SaveReportShellValues(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}
	
	// private:
	
	public SaveReportShellValues() {
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}
}
