package org.lissovski.metcmgenerator.ui;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.eclipse.swt.SWT;
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
import org.lissovski.metcmgenerator.generator.Floor;
import org.lissovski.metcmgenerator.ui.events.ExportReportEvent;
import org.lissovski.metcmgenerator.ui.events.ExportReportListener;
import org.lissovski.metcmgenerator.ui.events.GenerateReportEvent;
import org.lissovski.metcmgenerator.ui.events.GenerateReportListener;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class RootShell extends Shell {
    private RootShellValues settings;
    
    private List<GenerateReportListener> generateReportListeners = new Vector<GenerateReportListener>();
    private List<ExportReportListener> exportReportListeners = new Vector<ExportReportListener>();
    
    private class ExportReportEventValues {
        public List<Floor> floors;
        public RootShellValues rootShellValues;
    }
    private ExportReportEventValues exportReportEventValues = new ExportReportEventValues();
    
    private Text octantText;
    private Text locationText;
    private Text windSpeedText;
    private Text windDirectionText;
    private Text temperatureText;
    private Text airPressureText;
    private Text altitudeText;
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
        GridData gd = new GridData();
        gd.widthHint = 100; 
        
        input.setLayoutData(gd);
    }
    
    private void createGroundValuesGroup(Composite owningContainer) {
        Group groundValuesGroup = new Group(owningContainer, SWT.SHADOW_ETCHED_OUT);
        GridLayout groundValuesGroupLayout = new GridLayout(1, true);
        groundValuesGroup.setText("Ground values");
        groundValuesGroup.setLayout(groundValuesGroupLayout);
        
        final int inputMask = SWT.BORDER;
        
        Label octantLabel = new Label(groundValuesGroup, SWT.NULL);
        octantLabel.setText("Octant:");
        
        octantText = new Text(groundValuesGroup, inputMask);
        octantText.setText(settings.getOctant());
        adjustWidth(octantText);
        
        Label locationLabel = new Label(groundValuesGroup, SWT.NULL);
        locationLabel.setText("Location:");
        
        locationText = new Text(groundValuesGroup, inputMask);
        locationText.setText(settings.getLocation());
        adjustWidth(locationText);
        
        Label windSpeedLabel = new Label(groundValuesGroup, SWT.NULL);
        windSpeedLabel.setText("Wind speed (kn):");
        
        windSpeedText = new Text(groundValuesGroup, inputMask);
        windSpeedText.setText(settings.getWindSpeed());
        adjustWidth(windSpeedText);
        
        Label windDirectionLabel = new Label(groundValuesGroup, SWT.NULL);
        windDirectionLabel.setText("Wind direction:");
        
        windDirectionText = new Text(groundValuesGroup, inputMask);
        windDirectionText.setText(settings.getWindDirection());
        adjustWidth(windDirectionText);
        
        Label temperatureLabel = new Label(groundValuesGroup, SWT.NULL);
        temperatureLabel.setText("Temperature (C):");
        
        temperatureText = new Text(groundValuesGroup, inputMask);
        temperatureText.setText(settings.getTemperature());
        adjustWidth(temperatureText);
        
        Label airPressureLabel = new Label(groundValuesGroup, SWT.NULL);
        airPressureLabel.setText("Air pressure:");
        
        airPressureText = new Text(groundValuesGroup, inputMask);
        airPressureText.setText(settings.getAirPressure());
        adjustWidth(airPressureText);
        
        Label altitudeLabel = new Label(groundValuesGroup, SWT.NULL);
        altitudeLabel.setText("Altitude (m):");
        
        altitudeText = new Text(groundValuesGroup, inputMask);
        altitudeText.setText(settings.getAltitude());
        adjustWidth(altitudeText);
        
        GridData resetGroundValuesButtonLayoutData = new GridData();
        resetGroundValuesButtonLayoutData.horizontalAlignment = SWT.CENTER;
        Button resetGroundValuesButton = new Button(groundValuesGroup, SWT.NULL);
        resetGroundValuesButton.setText("Reset");
        resetGroundValuesButton.setLayoutData(resetGroundValuesButtonLayoutData);
        
        resetGroundValuesButton.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                octantText.setText("");
                locationText.setText("");
                windSpeedText.setText("");
                windDirectionText.setText("");
                temperatureText.setText("");
                airPressureText.setText("");
                altitudeText.setText("");
            }
        });
    }
    
    private void createDateTimeGroup(Composite owningContainer) {
        Group dateTimeGroup = new Group(owningContainer, SWT.SHADOW_ETCHED_OUT);
        dateTimeGroup.setLayout(new RowLayout(SWT.VERTICAL));
        dateTimeGroup.setText("Time");
        
        dateInput = new DateTime(dateTimeGroup, SWT.DATE | SWT.BORDER);
        timeInput = new DateTime(dateTimeGroup, SWT.TIME | SWT.BORDER);
    }

    public RootShell(Display display, RootShellValues settings) {
        super(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
        setText("METCM generator");
        
        this.settings = settings;
        
        setLayout(new GridLayout(2, false));
        
        Composite leftContainer = new Composite(this, SWT.NULL);
        RowLayout leftContainerLayout = new RowLayout(SWT.VERTICAL);
//        leftContainerLayout.fill = true;
        leftContainer.setLayout(leftContainerLayout);
        
        createGroundValuesGroup(leftContainer);
        createDateTimeGroup(leftContainer);
        
        GridData reportsGroupLayoutData = new GridData();
        reportsGroupLayoutData.verticalAlignment = SWT.FILL;
        Group reportsGroup = new Group(this, SWT.SHADOW_ETCHED_OUT);
        reportsGroup.setLayoutData(reportsGroupLayoutData);
        reportsGroup.setLayout(new RowLayout(SWT.VERTICAL));
        reportsGroup.setText("Report");
        
        reportTable = new Table(reportsGroup, SWT.BORDER | SWT.V_SCROLL);
        reportTable.setHeaderVisible(true);
        RowData reportTableLayoutData = new RowData();
        reportTableLayoutData.height = 405;
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
        floorsCountText.setText(settings.getFloorsCount());
        adjustWidth(floorsCountText, 3);
        
        Button generateButton = new Button(actionButtonsGroup, SWT.NULL);
        generateButton.setText("Generate");
        generateButton.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event arg0) {
                Calendar year = Calendar.getInstance();
                year.set(Calendar.DAY_OF_MONTH, dateInput.getDay());
                year.set(Calendar.MONTH, dateInput.getMonth());
                year.set(Calendar.YEAR, dateInput.getYear());
                
                Calendar time = Calendar.getInstance();
                time.set(Calendar.HOUR_OF_DAY, timeInput.getHours());
                time.set(Calendar.MINUTE, timeInput.getMinutes());
                
                exportReportEventValues.rootShellValues = new RootShellValues(
                    octantText.getText(), 
                    locationText.getText(), 
                    windSpeedText.getText(), 
                    windDirectionText.getText(),
                    temperatureText.getText(),
                    airPressureText.getText(), 
                    altitudeText.getText(),
                    year.getTime(), 
                    time.getTime(), 
                    floorsCountText.getText()
                );
                
                GenerateReportEvent event = new GenerateReportEvent(this, exportReportEventValues.rootShellValues);
                for (GenerateReportListener listener : generateReportListeners) {
                    listener.generateReport(event);        
                }
            }
        });
        
        exportButton = new Button(actionButtonsGroup, SWT.NULL);
        exportButton.setEnabled(false); // it must not be possible to export report unless it is generated
        exportButton.setText("Export");
        exportButton.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event arg0) {
                ExportReportEvent event = new ExportReportEvent(
                    this, 
                    exportReportEventValues.rootShellValues, 
                    exportReportEventValues.floors
                );
                
                for (ExportReportListener listener : exportReportListeners) {
                    listener.exportReport(event);
                }
            }
        });
    }
    
    protected void checkSubclass() {
    }
    
    public void reloadReportRows(List<Floor> rows) {
        exportReportEventValues.floors = rows;
        
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
