package de.carsten_lenz.liquibasegui.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;

public class RunPropertiesComposite extends Composite {
    private Text nameTxt;
    private Text text;
    private Text usernameTxt;
    private Text passwordTxt;
    private Text text_1;
    private Text text_2;
    private Table table;
    private Table table_1;

    /**
     * Create the composite.
     * @param parent
     * @param style
     */
    public RunPropertiesComposite(Composite parent, int style) {
        super(parent, style);
        setLayout(new FillLayout(SWT.HORIZONTAL));
        
        SashForm sashForm = new SashForm(this, SWT.NONE);
        
        Composite leftComposite = new Composite(sashForm, SWT.BORDER);
        GridLayout gl_leftComposite = new GridLayout(1, false);
        gl_leftComposite.horizontalSpacing = 0;
        gl_leftComposite.marginWidth = 0;
        gl_leftComposite.marginHeight = 0;
        gl_leftComposite.verticalSpacing = 0;
        leftComposite.setLayout(gl_leftComposite);
        
        ToolBar toolBar = new ToolBar(leftComposite, SWT.FLAT | SWT.RIGHT);
        toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        ListViewer listViewer = new ListViewer(leftComposite, SWT.BORDER | SWT.V_SCROLL);
        List list = listViewer.getList();
        list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        
        Composite rightComposite = new Composite(sashForm, SWT.NONE);
        GridLayout gl_rightComposite = new GridLayout(2, false);
        gl_rightComposite.horizontalSpacing = 10;
        gl_rightComposite.marginHeight = 10;
        gl_rightComposite.marginWidth = 10;
        gl_rightComposite.verticalSpacing = 10;
        rightComposite.setLayout(gl_rightComposite);
        
        Label lblName = new Label(rightComposite, SWT.NONE);
        lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblName.setText("Name:");
        
        nameTxt = new Text(rightComposite, SWT.BORDER);
        nameTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        CTabFolder tabFolder = new CTabFolder(rightComposite, SWT.BORDER);
        tabFolder.setSimple(false);
        tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));
        tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
        
        CTabItem tbtmDatabase = new CTabItem(tabFolder, SWT.NONE);
        tbtmDatabase.setText("Database");
        
        Composite databaseComposite = new Composite(tabFolder, SWT.NONE);
        tbtmDatabase.setControl(databaseComposite);
        databaseComposite.setLayout(new GridLayout(2, false));
        
        Label lblConnectionString = new Label(databaseComposite, SWT.NONE);
        lblConnectionString.setText("Connection String:");
        
        text = new Text(databaseComposite, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        Label lblDriverClassName = new Label(databaseComposite, SWT.NONE);
        lblDriverClassName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblDriverClassName.setText("Driver class name:");
        
        text_2 = new Text(databaseComposite, SWT.BORDER);
        text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        Label lblUsername = new Label(databaseComposite, SWT.NONE);
        lblUsername.setText("Username:");
        
        usernameTxt = new Text(databaseComposite, SWT.BORDER);
        usernameTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        Label lblPassword = new Label(databaseComposite, SWT.NONE);
        lblPassword.setText("Password:");
        
        passwordTxt = new Text(databaseComposite, SWT.BORDER | SWT.PASSWORD);
        passwordTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        Label label = new Label(databaseComposite, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.CENTER);
        label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
        
        Label lblChangelogfile = new Label(databaseComposite, SWT.NONE);
        lblChangelogfile.setText("Changelog file:");
        
        Composite composite = new Composite(databaseComposite, SWT.NONE);
        GridLayout gl_composite = new GridLayout(2, false);
        gl_composite.horizontalSpacing = 0;
        gl_composite.verticalSpacing = 0;
        gl_composite.marginWidth = 0;
        gl_composite.marginHeight = 0;
        composite.setLayout(gl_composite);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
        composite.setBounds(0, 0, 64, 64);
        
        text_1 = new Text(composite, SWT.BORDER);
        text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        Button btnBrowse = new Button(composite, SWT.NONE);
        btnBrowse.setText("Browse...");
        
        Label lblProperties = new Label(databaseComposite, SWT.NONE);
        lblProperties.setText("Properties:");
        new Label(databaseComposite, SWT.NONE);
        
        Composite composite_1 = new Composite(databaseComposite, SWT.NONE);
        TableColumnLayout tcl_composite_1 = new TableColumnLayout();
        composite_1.setLayout(tcl_composite_1);
        composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
        
        TableViewer tableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
        table_1 = tableViewer.getTable();
        table_1.setHeaderVisible(true);
        table_1.setLinesVisible(true);
        
        TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn tblclmnKey = tableViewerColumn.getColumn();
        tcl_composite_1.setColumnData(tblclmnKey, new ColumnPixelData(150, true, true));
        tblclmnKey.setText("Key");
        
        TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn tblclmnValue = tableViewerColumn_1.getColumn();
        tcl_composite_1.setColumnData(tblclmnValue, new ColumnPixelData(150, true, true));
        tblclmnValue.setText("Value");
        new Label(databaseComposite, SWT.NONE);
        
        sashForm.setWeights(new int[] {142, 455});

    }

    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }
}
