package de.carsten_lenz.liquibasegui.ui;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

class CommandView extends ViewPart {

    public static final String ID = "de.carsten_lenz.liquibasegui.commandView";

    @Override
    public void createPartControl(Composite parent) {
        GridLayout gridLayout = new GridLayout();
        parent.setLayout(gridLayout);

        ComboViewer configComboViewer = new ComboViewer(parent, SWT.NONE);

        GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        configComboViewer.getCombo().setLayoutData(gd);
    }

    @Override
    public void setFocus() {
    }
}
