package org.lissovski.metcmgenerator.ui;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class SaveReportShellValues {
	private String directoryPath;
	private boolean prettyPrint;
	private String filenamePattern;

	public SaveReportShellValues(String directoryPath, boolean prettyPrint, String filenamePattern) {
		this.directoryPath = directoryPath;
		this.prettyPrint = prettyPrint;
		this.filenamePattern = filenamePattern;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}
	
	public boolean isPrettyPrint() {
		return prettyPrint;
	}
	
	public String getFilenamePattern() {
		return filenamePattern;
	}
	
	// private:
	
	public SaveReportShellValues() {
	}

	public void setPrettyPrint(boolean prettyPrint) {
		this.prettyPrint = prettyPrint;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public void setFilenamePattern(String filenamePattern) {
		this.filenamePattern = filenamePattern;
	}
}
