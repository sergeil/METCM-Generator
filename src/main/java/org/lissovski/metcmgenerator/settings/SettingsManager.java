package org.lissovski.metcmgenerator.settings;

import java.io.File;

import org.codehaus.jackson.map.ObjectMapper;
import org.lissovski.metcmgenerator.ui.RootShellValues;
import org.lissovski.metcmgenerator.ui.SaveReportShellValues;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class SettingsManager {
	private ObjectMapper mapper = new ObjectMapper();
	
	private File getSettingsFile() {
		File file = new File(".");
		String currentLocation = file.getAbsolutePath() + "/";
		
		return  new File(currentLocation + "settings.json");
	}
	
	public SettingsManager() {
		File settingsFile = getSettingsFile();
		
		if (!settingsFile.exists() || settingsFile.isDirectory()) {
			save(new ApplicationSettings(this, new RootShellValues(), new SaveReportShellValues()));
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
