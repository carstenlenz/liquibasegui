package de.carsten_lenz.liquibasegui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "de.carsten_lenz.liquibasegui.perspective";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
		layout.addStandaloneView(NavigationView.ID,  false, IPageLayout.LEFT, 0.25f, editorArea);
		IFolderLayout folder = layout.createFolder("messages", IPageLayout.TOP, 0.5f, editorArea);
		folder.addPlaceholder(View.ID + ":*");
		folder.addView(View.ID);
		
		IFolderLayout consoleFolder = layout.createFolder("console",  
		        IPageLayout.BOTTOM, 0.65f, "messages");  
		consoleFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);  
		
		layout.getViewLayout(NavigationView.ID).setCloseable(false);
	}
}
