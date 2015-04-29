package org.lissovski.metcmgenerator.ui;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.lissovski.metcmgenerator.generator.GeneratorInput;
import org.lissovski.metcmgenerator.generator.Floor;
import org.lissovski.metcmgenerator.ui.events.ExportReportEvent;
import org.lissovski.metcmgenerator.ui.events.ExportReportListener;
import org.lissovski.metcmgenerator.ui.events.GenerateReportEvent;
import org.lissovski.metcmgenerator.ui.events.GenerateReportListener;

public class RootShell extends Shell {
	private GeneratorInput settings;
	
	private List<GenerateReportListener> generateReportListeners = new Vector<GenerateReportListener>();
	private List<ExportReportListener> exportReportListeners = new Vector<ExportReportListener>();
	
	private class ExportReportEventValues {
		public List<Floor> rows;
		public GeneratorInput generatorConfigurationData;
	}
	private ExportReportEventValues exportReportEventValues = new ExportReportEventValues();
	
	private Text octantText;
	private Text locationText;
	private Text windSpeedText;
	private Text windDirectionText;
	private Text airPressureText;
	private Table reportTable;
	private DateTime dateInput;
	private DateTime timeInput;
	private Text floorsCountText;
	
	private Button exportButton;
	
	public void addGenerateReportListener(GenerateReportListener listener) {
		generateReportListeners.add(listener);
	}
	
	public void addExportReportListener(ExportReportListener listener) {
		exportReportListeners.add(listener);
	}
	
	private void adjustWidth(Text input) {
		adjustWidth(input, input.getMessage().length() + 4);
	}
	
	private void adjustWidth(Text input, Integer numberOfChars) {
		GC gc = new GC(input);
		FontMetrics fm = gc.getFontMetrics();
		
		GridData gd = new GridData();
		gd.widthHint = fm.getAverageCharWidth() * numberOfChars; 
		
		input.setLayoutData(gd);
	}
	
	private void createGroundValuesGroup(Composite owningContainer) {
		Group groundValuesGroup = new Group(owningContainer, SWT.SHADOW_ETCHED_OUT);
        GridLayout groundValuesGroupLayout = new GridLayout(5, false);
        groundValuesGroup.setText("Ground values");
        groundValuesGroup.setLayout(groundValuesGroupLayout);
        
        final int inputMask = SWT.BORDER;
        
        octantText = new Text(groundValuesGroup, inputMask);
        octantText.setMessage("Octant");
        if (settings.getOctant() != null) {
        	octantText.setText(Integer.toString(settings.getOctant()));        	
        }
        adjustWidth(octantText);
        
        locationText = new Text(groundValuesGroup, inputMask);
        locationText.setMessage("Location");
        if (settings.getLocation() != null) {
        	locationText.setText(Integer.toString(settings.getLocation()));
        }
        adjustWidth(locationText);
        
        windSpeedText = new Text(groundValuesGroup, inputMask);
        windSpeedText.setMessage("Wind speed");
        if (settings.getWindSpeed() != null) {
        	windSpeedText.setText(Double.toString(settings.getWindSpeed()));
        }
        adjustWidth(windSpeedText);
        
        windDirectionText = new Text(groundValuesGroup, inputMask);
        windDirectionText.setMessage("Wind direction");
        if (settings.getWindDirection() != null) {
        	windDirectionText.setText(Double.toString(settings.getWindDirection()));
        }
        adjustWidth(windDirectionText);
        
        airPressureText = new Text(groundValuesGroup, inputMask);
        airPressureText.setMessage("Air pressure");
        if (settings.getAirPressure() != null) {
        	airPressureText.setText(Double.toString(settings.getAirPressure()));
        }
        adjustWidth(airPressureText);
	}
	
	private void createDateTimeGroup(Composite owningContainer) {
		Group dateTimeGroup = new Group(owningContainer, SWT.SHADOW_ETCHED_OUT);
        dateTimeGroup.setLayout(new RowLayout(SWT.HORIZONTAL));
        dateTimeGroup.setText("Time");
        
        dateInput = new DateTime(dateTimeGroup, SWT.DATE | SWT.BORDER);
        timeInput = new DateTime(dateTimeGroup, SWT.TIME | SWT.BORDER);
	}

	public RootShell(Display display, GeneratorInput settings) {
		super(display);
		
		this.settings = settings;
        
        Composite topContainer = new Composite(this, SWT.NULL);
        RowLayout topContainerLayout = new RowLayout(SWT.HORIZONTAL);
        topContainerLayout.fill = true;
        topContainer.setLayout(topContainerLayout);
        
        createGroundValuesGroup(topContainer);
        createDateTimeGroup(topContainer);
        
        Group reportsGroup = new Group(this, SWT.SHADOW_ETCHED_OUT);
        reportsGroup.setLayout(new RowLayout(SWT.VERTICAL));
        reportsGroup.setText("Report");
        
        reportTable = new Table(reportsGroup, SWT.BORDER | SWT.V_SCROLL);
        reportTable.setHeaderVisible(true);
        RowData reportTableLayoutData = new RowData();
        reportTableLayoutData.height = 250;
        reportTable.setLayoutData(reportTableLayoutData);
        
        String [] columns = {
    		"Floor", "Wind direction", "Wind speed", "Temperature", "Air pressure"
		};
        for (int i = 0; i < columns.length; i++) {
        	TableColumn column = new TableColumn(reportTable, SWT.CENTER);
        	column.setText(columns[i]);
        	column.setWidth(118);
    	}
        
        GridLayout actionButtonsLayout = new GridLayout(4, false);
        Composite actionButtonsGroup = new Composite(reportsGroup, SWT.NULL);
        actionButtonsGroup.setLayout(actionButtonsLayout);
        
        Label floorsCountLabel = new Label(actionButtonsGroup, SWT.CENTER);
        floorsCountLabel.setText("Floors");
        
        floorsCountText = new Text(actionButtonsGroup, SWT.BORDER);
        if (settings.getFloorsCount() != null) {
        	floorsCountText.setText(Integer.toString(settings.getFloorsCount()));
        }
        adjustWidth(floorsCountText, 3);
        
        Button generateButton = new Button(actionButtonsGroup, SWT.NULL);
        generateButton.setText("Generate");
        generateButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				if (isGroundValuesSetValid()) {
					exportReportEventValues.generatorConfigurationData = createGeneratorInput();
					
					GenerateReportEvent event = new GenerateReportEvent(this, exportReportEventValues.generatorConfigurationData);
					for (GenerateReportListener listener : generateReportListeners) {
						listener.generateReport(event);		
					}
				}
			}
        });
        
        exportButton = new Button(actionButtonsGroup, SWT.NULL);
        exportButton.setEnabled(false);
        exportButton.setText("Export");
        exportButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				if (isReportTableValid()) {
					ExportReportEvent event = new ExportReportEvent(
						this, 
						exportReportEventValues.generatorConfigurationData, 
						exportReportEventValues.rows
					);
					
					for (ExportReportListener listener : exportReportListeners) {
						listener.exportReport(event);
					}
				}
			}
        });
       
        setLayout(new RowLayout(SWT.VERTICAL));
	}
	
	private boolean isReportTableValid() {
		return true;
	}
	
	private boolean isGroundValuesSetValid() {
		Text[] fields = { octantText, locationText, windSpeedText, windDirectionText, airPressureText };
		
		for (int i=0; i<fields.length; i++) {
			Text field = fields[i];
			if (field.getText().equals("")) {
				return false;
			}
		}
		
		// FIXME validate if Integers, Doubles
		
		return true;
	}
	
	private GeneratorInput createGeneratorInput() {
		Calendar cal = Calendar.getInstance();
		cal.set(
			dateInput.getYear(), dateInput.getMonth(), dateInput.getDay(), 
			timeInput.getHours(), timeInput.getMinutes()
		);
		
		return new GeneratorInput(
			Integer.parseInt(octantText.getText()),
			Integer.parseInt(locationText.getText()), 
			Double.parseDouble(windSpeedText.getText()), 
			Double.parseDouble(windDirectionText.getText()), 
			Double.parseDouble(airPressureText.getText()),
			cal.getTime(),
			Integer.parseInt(floorsCountText.getText())
		);
	}
	
	protected void checkSubclass() {
	}
	
	public void reloadReportRows(List<Floor> rows) {
		exportReportEventValues.rows = rows;
		
		reportTable.removeAll();
		
		for (Floor row : rows) {
			TableItem item = new TableItem(reportTable, SWT.NULL);
			item.setText(0, row.getFloor().toString());
			item.setText(1, row.getWindDirection().toString());
			item.setText(2, row.getWindSpeed().toString());
			item.setText(3, row.getTemperature().toString());
			item.setText(4, row.getAirPressure().toString());
		}
	}
	
	public void setExportEnabled(boolean isExportEnabled) {
		exportButton.setEnabled(isExportEnabled);
	}
}
