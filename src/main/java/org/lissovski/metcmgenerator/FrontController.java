package org.lissovski.metcmgenerator;

import java.util.List;
import java.util.Vector;

import org.eclipse.swt.widgets.Display;
import org.lissovski.metcmgenerator.exporter.ReportExporter;
import org.lissovski.metcmgenerator.generator.GeneratorImpl;
import org.lissovski.metcmgenerator.generator.GeneratorInputSpecification;
import org.lissovski.metcmgenerator.generator.GeneratorOutput;
import org.lissovski.metcmgenerator.generator.ReportGenerator;
import org.lissovski.metcmgenerator.settings.ApplicationSettings;
import org.lissovski.metcmgenerator.settings.SettingsManager;
import org.lissovski.metcmgenerator.ui.ExportDialog;
import org.lissovski.metcmgenerator.ui.RootShell;
import org.lissovski.metcmgenerator.ui.events.ExportReportEvent;
import org.lissovski.metcmgenerator.ui.events.ExportReportListener;
import org.lissovski.metcmgenerator.ui.events.GenerateReportEvent;
import org.lissovski.metcmgenerator.ui.events.GenerateReportListener;
import org.lissovski.metcmgenerator.ui.events.SaveReportEvent;
import org.lissovski.metcmgenerator.ui.events.SaveReportListener;

public class FrontController {
	
	public static void main(String[] args) {
        ReportGenerator generator = new GeneratorImpl();
        ReportExporter exporter = new ReportExporter();
        SettingsManager settingsManager = new SettingsManager();
        
        ApplicationSettings appSettings = settingsManager.load();
        
        Display display = new Display();
        RootShell rootShell = new RootShell(display, appSettings.getGeneratorShellSettings());
        
        List<GeneratorOutput> generatorOutputs = new Vector<GeneratorOutput>();
        
        // triggering report generation
        rootShell.addGenerateReportListener(new GenerateReportListener() {
			public void generateReport(GenerateReportEvent event) {
				GeneratorOutput output = generator.generate(event.getGeneratorInput());
				rootShell.reloadReportRows(output.getFloors());
				
				generatorOutputs.add(output);
			}
		});
        
        // updating settings
        rootShell.addGenerateReportListener(new GenerateReportListener() {
			@Override
			public void generateReport(GenerateReportEvent event) {
				List<String> validationErrors = GeneratorInputSpecification.validate(event.getGeneratorInput());
				
				System.out.println(" >>>> ");
				System.out.println(validationErrors);
				
				if (validationErrors.size() == 0) {
					appSettings.setGeneratorShellSettings(event.getGeneratorInput());
					appSettings.save();
					
					rootShell.setExportEnabled(true);					
				} else {
					System.out.println("errors: " + validationErrors);
				}
			}
		});
        
        rootShell.addExportReportListener(new ExportReportListener() {
			public void exportReport(ExportReportEvent event) {
				ExportDialog dialog = new ExportDialog(rootShell, appSettings.getSaveReportShellSettings());
				
				// physical export
				dialog.addSaveReportListener(new SaveReportListener() {
					@Override
					public void saveReport(SaveReportEvent event) {
						GeneratorOutput lastOutput = generatorOutputs.get(generatorOutputs.size()-1);
						exporter.export(lastOutput, event.getSaveReportConfig().getDirectoryPath());
						
						dialog.close();
					}
				});
				
				// updating settings
				dialog.addSaveReportListener(new SaveReportListener() {
					@Override
					public void saveReport(SaveReportEvent event) {
						appSettings.setSaveReportShellSettings(event.getSaveReportConfig());
						appSettings.save();
					}
				});
				
				dialog.open();
			}
		});
        
        rootShell.pack();
        rootShell.open();
        
        while (!rootShell.isDisposed()) {
            if (!display.readAndDispatch()) {
            	display.sleep();
            }
        }
        display.dispose();
	}
}
