package de.carsten_lenz.liquibasegui.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class TestView extends ViewPart {

    public static final String ID = "de.carsten_lenz.liquibasegui.ui.TestView"; //$NON-NLS-1$
    private Text text;

    public TestView() {
    }

    /**
     * Create contents of the view part.
     * @param parent
     */
    @Override
    public void createPartControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false));
        Label lblTollerText = new Label(container, SWT.NONE);
        lblTollerText.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblTollerText.setText("Toller Text:");
        text = new Text(container, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    }

    @Override
    public void setFocus() {
        // Set the focus
    }

}
