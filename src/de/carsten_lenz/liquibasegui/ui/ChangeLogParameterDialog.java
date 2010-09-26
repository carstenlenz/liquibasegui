package de.carsten_lenz.liquibasegui.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.carsten_lenz.liquibasegui.model.ChangeLogParameter;

public class ChangeLogParameterDialog extends Dialog {
    private Text keyText;
    private Text valueText;
    private ChangeLogParameter changeLogParameter;

    /**
     * Create the dialog.
     * @param parentShell
     */
    public ChangeLogParameterDialog(Shell parentShell) {
        super(parentShell);
    }

    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        
        getShell().setText("Changelog Parameter");
        
        GridLayout gridLayout = (GridLayout) container.getLayout();
        gridLayout.numColumns = 2;
        
        Label lblKey = new Label(container, SWT.NONE);
        lblKey.setText("Key:");
        
        keyText = new Text(container, SWT.BORDER);
        keyText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        keyText.setText(changeLogParameter.getKey() != null ? changeLogParameter.getKey() : "");
        
        Label lblValue = new Label(container, SWT.NONE);
        lblValue.setText("Value:");
        
        valueText = new Text(container, SWT.BORDER);
        valueText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        valueText.setText(changeLogParameter.getValue() != null ? changeLogParameter.getValue().toString() : "");
        
        return container;
    }
    
    public void setChangeLogParameter(ChangeLogParameter parameter) {
        this.changeLogParameter = parameter;
    }
    
    @Override
    public int open() {
        if (changeLogParameter ==  null) {
            throw new NullPointerException("changeLogParameter must not be null");
        }
        return super.open();
    }
    
    @Override
    protected void okPressed() {
        changeLogParameter.setKey(keyText.getText());
        changeLogParameter.setValue(valueText.getText());
        super.okPressed();
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
        return new Point(450, 300);
    }

}
