package org.lissovski.metcmgenerator.ui;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class SaveReportShellValues {
	private String directoryPath;
	private boolean prettyPrint;

	public SaveReportShellValues(String directoryPath, boolean prettyPrint) {
		this.directoryPath = directoryPath;
		this.prettyPrint = prettyPrint;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}
	
	public boolean isPrettyPrint() {
		return prettyPrint;
	}
	
	// private:

	public void setPrettyPrint(boolean prettyPrint) {
		this.prettyPrint = prettyPrint;
	}

	public SaveReportShellValues() {
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}
}
