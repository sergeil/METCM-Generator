package org.lissovski.metcmgenerator.ui.events;

import java.util.EventObject;

import org.lissovski.metcmgenerator.ui.RootShellValues;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class GenerateReportEvent extends EventObject {
	private static final long serialVersionUID = 4859529483681982074L;
	
	private RootShellValues rootShellValues;
	
	public GenerateReportEvent(Object source, RootShellValues rootShellValues) {
		super(source);
		
		this.rootShellValues = rootShellValues;
	}

	public RootShellValues getRootShellValues() {
		return rootShellValues;
	}

	@Override
	public String toString() {
		return rootShellValues.toString();
	}
}
