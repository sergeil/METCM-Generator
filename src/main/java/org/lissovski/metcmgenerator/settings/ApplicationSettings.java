package org.lissovski.metcmgenerator.settings;

import org.lissovski.metcmgenerator.generator.GeneratorInput;
import org.lissovski.metcmgenerator.ui.SaveReportConfig;

public class ApplicationSettings {
	private SettingsManager settingsManager;
	
	private GeneratorInput generatorShellSettings;
	private SaveReportConfig saveReportShellSettings;
	
	public ApplicationSettings(SettingsManager settingsManager,
			GeneratorInput generatorShellSettings,
			SaveReportConfig saveReportShellSettings) {
		
		this.settingsManager = settingsManager;
		this.generatorShellSettings = generatorShellSettings;
		this.saveReportShellSettings = saveReportShellSettings;
	}
	
	public GeneratorInput getGeneratorShellSettings() {
		return generatorShellSettings;
	}
	
	public SaveReportConfig getSaveReportShellSettings() {
		return saveReportShellSettings;
	}

	public void save() {
		settingsManager.save(this);
	}
	
	public void init(SettingsManager settingsManager) {
		this.settingsManager = settingsManager;
	}
	
	// private:
	
	public void setGeneratorShellSettings(GeneratorInput generatorShellSettings) {
		this.generatorShellSettings = generatorShellSettings;
	}

	public void setSaveReportShellSettings(SaveReportConfig saveReportShellSettings) {
		this.saveReportShellSettings = saveReportShellSettings;
	}
	
	public ApplicationSettings() {
	}
}
