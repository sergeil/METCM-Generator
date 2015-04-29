package org.lissovski.metcmgenerator.settings;

import java.io.File;

import org.codehaus.jackson.map.ObjectMapper;
import org.lissovski.metcmgenerator.generator.GeneratorInput;
import org.lissovski.metcmgenerator.ui.SaveReportConfig;

public class SettingsManager {
	private ObjectMapper mapper = new ObjectMapper();
	
	private File getSettingsFile() {
		String currentLocation = ClassLoader.getSystemClassLoader().getResource(".").getPath();
		
		return  new File(currentLocation + "settings.json");
	}
	
	public SettingsManager() {
		File settingsFile = getSettingsFile();
		
		if (!settingsFile.exists() || settingsFile.isDirectory()) {
			save(new ApplicationSettings(this, new GeneratorInput(), new SaveReportConfig()));
		}
	}

	public void save(ApplicationSettings settings) {
		try {
			mapper.writeValue(getSettingsFile(), settings);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
	}
	
	public ApplicationSettings load() {
		ApplicationSettings result = null;
		
		try {
			result = mapper.readValue(getSettingsFile(), ApplicationSettings.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result.init(this);
		
		return result;
	}
}
