package org.lissovski.metcmgenerator.settings;

import org.lissovski.metcmgenerator.ui.RootShellValues;
import org.lissovski.metcmgenerator.ui.SaveReportShellValues;

public class ApplicationSettings {
	private SettingsManager settingsManager;
	
	private RootShellValues rootShellSettings;
	private SaveReportShellValues saveReportShellSettings;
	
	public ApplicationSettings(SettingsManager settingsManager,
			RootShellValues rootShellSettings,
			SaveReportShellValues saveReportShellSettings) {
		
		this.settingsManager = settingsManager;
		this.rootShellSettings = rootShellSettings;
		this.saveReportShellSettings = saveReportShellSettings;
	}

	public void save() {
		settingsManager.save(this);
	}
	
	public void init(SettingsManager settingsManager) {
		this.settingsManager = settingsManager;
	}
	
	public RootShellValues getRootShellSettings() {
		return rootShellSettings;
	}

	public SaveReportShellValues getSaveReportShellSettings() {
		return saveReportShellSettings;
	}
	
	// private:

	public ApplicationSettings() {
	}

	public void setRootShellSettings(RootShellValues rootShellSettings) {
		this.rootShellSettings = rootShellSettings;
	}

	public void setSaveReportShellSettings(SaveReportShellValues saveReportShellSettings) {
		this.saveReportShellSettings = saveReportShellSettings;
	}
}
