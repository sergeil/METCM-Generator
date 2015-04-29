package org.lissovski.metcmgenerator.ui;

public class SaveReportConfig {
	private String directoryPath;

	public SaveReportConfig(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}
	
	// private:
	
	public SaveReportConfig() {
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}
}
