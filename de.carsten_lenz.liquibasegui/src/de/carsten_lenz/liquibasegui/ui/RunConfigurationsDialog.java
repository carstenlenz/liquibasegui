package de.carsten_lenz.liquibasegui.ui;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import de.carsten_lenz.liquibasegui.model.RunConfiguration;

public class RunConfigurationsDialog extends TitleAreaDialog {

    private List<RunConfiguration> configurations;
    
    private RunConfigurationsComposite runConfigurationsComposite;

    /**
     * Create the dialog.
     * @param parentShell
     */
    public RunConfigurationsDialog(Shell parentShell) {
        super(parentShell);
        setShellStyle(SWT.DIALOG_TRIM | SWT.RESIZE | SWT.APPLICATION_MODAL);
    }

    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        setTitle("Run Configurations");
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayout(new FillLayout(SWT.HORIZONTAL));
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        runConfigurationsComposite = new RunConfigurationsComposite(container, SWT.NONE);
        runConfigurationsComposite.setRunConfigurations(configurations);

        return area;
    }
    
    public void setRunConfigurations(List<RunConfiguration> configurations) {
        this.configurations = configurations;
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
                true);
        createButton(parent, IDialogConstants.CANCEL_ID,
                IDialogConstants.CANCEL_LABEL, false);
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(570, 475);
    }
}
