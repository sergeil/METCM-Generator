package org.lissovski.metcmgenerator.ui;

import java.util.List;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.lissovski.metcmgenerator.ui.events.SaveReportEvent;
import org.lissovski.metcmgenerator.ui.events.SaveReportListener;

public class ExportDialog extends Dialog {
	Shell shell;
	
	private List<SaveReportListener> saveReportListeners = new Vector<SaveReportListener>();
	
	public void addSaveReportListener(SaveReportListener listener) {
		saveReportListeners.add(listener);
	}
	
	public ExportDialog(Shell parent, SaveReportConfig settings) {
		super(parent);

		shell = new Shell(parent);
		shell.setLayout(new GridLayout(3, false));
		shell.setText("Choose a directory where to save a report ...");
		
		Label label = new Label(shell, SWT.NULL);
		label.setText("Output directory:");
		
		GridData outputDirLayoutData = new GridData();
		outputDirLayoutData.widthHint = 250;
		Text outputDirText = new Text(shell, SWT.BORDER);
		if (settings.getDirectoryPath() != null) {
			outputDirText.setText(settings.getDirectoryPath());
		}
		outputDirText.setLayoutData(outputDirLayoutData);
		
		Button chooseDirButton = new Button(shell, SWT.NULL);
		chooseDirButton.setText("...");
		
		chooseDirButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				DirectoryDialog dlg = new DirectoryDialog(shell);
				
				if (settings.getDirectoryPath() != null && !settings.getDirectoryPath().equals("")) {
					dlg.setFilterPath(settings.getDirectoryPath());
				}
				
				String dirPath = dlg.open();
				if (dirPath != null) {
					outputDirText.setText(dirPath);
				}
			}
		}); 
		
		// FIXME position buttons in a center of the buttonsContainer
		GridData buttonsGroupLayoutData = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		buttonsGroupLayoutData.horizontalSpan = 3;
		
		Group buttonsGroup = new Group(shell, SWT.NULL);
		buttonsGroup.setLayout(new GridLayout(1, true));
		buttonsGroup.setLayoutData(buttonsGroupLayoutData);		
		
		Composite buttonsComposite = new Composite(buttonsGroup, SWT.NULL);
		buttonsComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Button cancelButton = new Button(buttonsComposite, SWT.PUSH);
		cancelButton.setText("Cancel");
		cancelButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				close();
			}
		});
		
		Button saveButton = new Button(buttonsComposite, SWT.PUSH);
		saveButton.setText("Save");
		saveButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				if (!outputDirText.getText().equals("")) {
					SaveReportEvent event = new SaveReportEvent(this, new SaveReportConfig(outputDirText.getText()));
					
					for (SaveReportListener listener : saveReportListeners) {
						listener.saveReport(event);
					}
				}
			}
		});
		
		shell.pack();
	}
	
	public void open() {
		shell.open();
	}
	
	public void close() {
		shell.close();
	}
}
