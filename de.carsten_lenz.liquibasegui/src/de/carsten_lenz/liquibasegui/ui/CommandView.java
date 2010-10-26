package de.carsten_lenz.liquibasegui.ui;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class CommandView extends ViewPart {
    public CommandView() {
    }

    public static final String ID = "de.carsten_lenz.liquibasegui.commandView";

    @Override
    public void createPartControl(Composite parent) {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        parent.setLayout(gridLayout);
        
        Label lblRunConfiguration = new Label(parent, SWT.NONE);
        lblRunConfiguration.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblRunConfiguration.setText("Run Configuration:");

        ComboViewer configComboViewer = new ComboViewer(parent, SWT.READ_ONLY);
        configComboViewer.getCombo().setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
        
        Label lblCommands = new Label(parent, SWT.NONE);
        lblCommands.setText("Commands:");
        
        Button btnUpdate = new Button(parent, SWT.NONE);
        btnUpdate.setText("Update");
    }

    @Override
    public void setFocus() {
    }
}
