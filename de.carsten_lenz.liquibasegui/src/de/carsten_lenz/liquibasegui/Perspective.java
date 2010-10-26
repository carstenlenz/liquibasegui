package de.carsten_lenz.liquibasegui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import de.carsten_lenz.liquibasegui.ui.CommandView;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "de.carsten_lenz.liquibasegui.perspective";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
		layout.addStandaloneView(CommandView.ID,  false, IPageLayout.TOP, 0.5f, editorArea);
		
		IFolderLayout consoleFolder = layout.createFolder("console",  
		        IPageLayout.BOTTOM, 0.5f, "messages");  
		consoleFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);  
		
		layout.getViewLayout(CommandView.ID).setCloseable(false);
	}
}
