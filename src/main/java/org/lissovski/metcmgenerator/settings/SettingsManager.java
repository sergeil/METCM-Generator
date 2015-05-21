package org.lissovski.metcmgenerator.settings;

import java.io.File;

import org.codehaus.jackson.map.ObjectMapper;
import org.lissovski.metcmgenerator.exporter.ReportExporter;
import org.lissovski.metcmgenerator.ui.RootShellValues;
import org.lissovski.metcmgenerator.ui.SaveReportShellValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class SettingsManager {
    private final Logger logger = LoggerFactory.getLogger(SettingsManager.class);
    
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
            logger.error("Unable to save application settings, error: " + e.getMessage());
        }
    }
    
    public ApplicationSettings load() {
        ApplicationSettings result = null;
        
        try {
            result = mapper.readValue(getSettingsFile(), ApplicationSettings.class);
        } catch (Exception e) {
            logger.error("Unable to load application's settings, error: " + e.getMessage());
        }
        
        result.init(this);
        
        return result;
    }
}
