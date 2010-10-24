package de.carsten_lenz.liquibasegui.ui;

import java.io.File;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import de.carsten_lenz.liquibasegui.model.ChangeLogParameter;
import de.carsten_lenz.liquibasegui.model.RunConfiguration;
import de.carsten_lenz.liquibasegui.util.FileToStringConverter;
import de.carsten_lenz.liquibasegui.util.StringToFileConverter;

public class RunConfigurationsComposite extends Composite {
    private DataBindingContext m_bindingContext;
    
    private IObservableList configurations;
    private IObservableValue currentConfiguration = new WritableValue();
    
    private Text nameTxt;
    private Text connectionStringText;
    private Text usernameText;
    private Text passwordText;
    private Text changeLogFilePathText;
    private Text driverClassNameText;
    private Table changeLogParametersTable;
    private ListViewer runConfigurationsListViewer;
    private TableViewer changeLogParametersTableViewer;

    private IObservableList currentChangeLogParameters;

    private Button btnNew;

    private Button btnEdit;

    private Button btnDelete;

    /**
     * Create the composite.
     * @param parent
     * @param style
     */
    public RunConfigurationsComposite(Composite parent, int style) {
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
        
        ToolItem tltmNew = new ToolItem(toolBar, SWT.NONE);
        tltmNew.setText("New");
        tltmNew.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                RunConfiguration runConfiguration = new RunConfiguration();
                runConfiguration.setConfigurationName("New Configuration");
                configurations.add(runConfiguration);
                runConfigurationsListViewer.refresh(null, true);
                runConfigurationsListViewer.setSelection(new StructuredSelection(runConfiguration));
            }
        });
        
        runConfigurationsListViewer = new ListViewer(leftComposite, SWT.BORDER | SWT.V_SCROLL);
        List runConfigurationsList = runConfigurationsListViewer.getList();
        runConfigurationsList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        
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
        
        connectionStringText = new Text(databaseComposite, SWT.BORDER);
        connectionStringText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        Label lblDriverClassName = new Label(databaseComposite, SWT.NONE);
        lblDriverClassName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblDriverClassName.setText("Driver class name:");
        
        driverClassNameText = new Text(databaseComposite, SWT.BORDER);
        driverClassNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        Label lblUsername = new Label(databaseComposite, SWT.NONE);
        lblUsername.setText("Username:");
        
        usernameText = new Text(databaseComposite, SWT.BORDER);
        usernameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        Label lblPassword = new Label(databaseComposite, SWT.NONE);
        lblPassword.setText("Password:");
        
        passwordText = new Text(databaseComposite, SWT.BORDER | SWT.PASSWORD);
        passwordText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
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
        
        changeLogFilePathText = new Text(composite, SWT.BORDER);
        changeLogFilePathText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        Button btnBrowse = new Button(composite, SWT.NONE);
        btnBrowse.setText("Browse...");
        btnBrowse.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog fileDialog = new FileDialog(getShell());
                fileDialog.setFilterExtensions(new String[] {"*.xml"});
                String selectedFilePath = fileDialog.open();
                if (selectedFilePath != null) {
                    changeLogFilePathText.setText(selectedFilePath);
                }
            }
        });
        
        Label lblProperties = new Label(databaseComposite, SWT.NONE);
        lblProperties.setText("Properties:");
        new Label(databaseComposite, SWT.NONE);
        
        Composite composite_2 = new Composite(databaseComposite, SWT.NONE);
        GridLayout gl_composite_2 = new GridLayout(2, false);
        gl_composite_2.verticalSpacing = 0;
        gl_composite_2.horizontalSpacing = 0;
        gl_composite_2.marginHeight = 0;
        gl_composite_2.marginWidth = 0;
        composite_2.setLayout(gl_composite_2);
        composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
        
        Composite composite_1 = new Composite(composite_2, SWT.NONE);
        composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        TableColumnLayout tcl_composite_1 = new TableColumnLayout();
        composite_1.setLayout(tcl_composite_1);
        
        changeLogParametersTableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
        changeLogParametersTable = changeLogParametersTableViewer.getTable();
        changeLogParametersTable.setHeaderVisible(true);
        changeLogParametersTable.setLinesVisible(true);
        
        TableViewerColumn tableViewerColumn = new TableViewerColumn(changeLogParametersTableViewer, SWT.NONE);
        TableColumn tblclmnKey = tableViewerColumn.getColumn();
        tcl_composite_1.setColumnData(tblclmnKey, new ColumnPixelData(150, true, true));
        tblclmnKey.setText("Key");
        
        TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(changeLogParametersTableViewer, SWT.NONE);
        TableColumn tblclmnValue = tableViewerColumn_1.getColumn();
        tcl_composite_1.setColumnData(tblclmnValue, new ColumnPixelData(150, true, true));
        tblclmnValue.setText("Value");
        
        Composite composite_3 = new Composite(composite_2, SWT.NONE);
        composite_3.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
        GridLayout gl_composite_3 = new GridLayout(1, false);
        gl_composite_3.verticalSpacing = 0;
        gl_composite_3.horizontalSpacing = 0;
        gl_composite_3.marginHeight = 0;
        gl_composite_3.marginWidth = 0;
        composite_3.setLayout(gl_composite_3);
        
        btnNew = new Button(composite_3, SWT.NONE);
        btnNew.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        btnNew.setText("New");
        btnNew.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ChangeLogParameterDialog dialog = new ChangeLogParameterDialog(getShell());
                ChangeLogParameter changeLogParameter = new ChangeLogParameter();
                dialog.setChangeLogParameter(changeLogParameter);
                if (dialog.open() == Window.OK) {
                    currentChangeLogParameters.add(changeLogParameter);
                }
                
            }
        });
        
        btnEdit = new Button(composite_3, SWT.NONE);
        btnEdit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        btnEdit.setText("Edit");
        btnEdit.setEnabled(false);
        btnEdit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ChangeLogParameter parameter = getSelectedChangeLogParameter();
                if (parameter != null) {
                    ChangeLogParameterDialog dialog = new ChangeLogParameterDialog(getShell());
                    dialog.setChangeLogParameter(parameter);
                    dialog.open();
                }
            }
        });
        
        btnDelete = new Button(composite_3, SWT.NONE);
        btnDelete.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ChangeLogParameter parameter = getSelectedChangeLogParameter();
                if (parameter != null) {
                    currentChangeLogParameters.remove(parameter);
                }
            }
        });
        
        changeLogParametersTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (event.getSelection() != null) {
                    btnEdit.setEnabled(true);
                    btnDelete.setEnabled(true);
                } else {
                    btnEdit.setEnabled(false);
                    btnDelete.setEnabled(false);
                }
            }
        });
        
        Composite composite_4 = new Composite(databaseComposite, SWT.NONE);
        GridLayout gl_composite_4 = new GridLayout(2, false);
        gl_composite_4.verticalSpacing = 0;
        gl_composite_4.horizontalSpacing = 0;
        gl_composite_4.marginHeight = 0;
        gl_composite_4.marginWidth = 0;
        composite_4.setLayout(gl_composite_4);
        composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
        
        final Button btnApply = new Button(composite_4, SWT.NONE);
        btnApply.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
        btnApply.setText("Apply");
        
        btnApply.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                applyPressed(btnApply);
            }
        });
        
        Button btnRevert = new Button(composite_4, SWT.NONE);
        btnRevert.setText("Revert");
        btnRevert.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                revertPressed();
            }
        });
        
        sashForm.setWeights(new int[] {142, 455});

        configurations = new WritableList();
        m_bindingContext = initDataBindings();
        
        currentChangeLogParameters = new WritableList();
        ViewerSupport.bind(changeLogParametersTableViewer, currentChangeLogParameters, PojoProperties.values(ChangeLogParameter.class, new String[]{"key", "value"}));
        
        IViewerObservableValue configSelection = ViewerProperties.singleSelection().observe(runConfigurationsListViewer);
        m_bindingContext.bindValue(configSelection, currentConfiguration);
        
        IObservableList validationStatusProviders = m_bindingContext.getValidationStatusProviders();
        for (Object o : validationStatusProviders) {
            ((ValidationStatusProvider)o).getValidationStatus().addChangeListener(new IChangeListener() {
                public void handleChange(ChangeEvent event) {
                    btnApply.setEnabled(true);
                }
            });
        }
        
        currentConfiguration.addValueChangeListener(new IValueChangeListener() {
            public void handleValueChange(ValueChangeEvent event) {
                btnApply.setEnabled(false);
                fillChangeLogParametersFromCurrentConfig();
            }
        });
        
        tabFolder.setSelection(tbtmDatabase);
    }
    
    public void setRunConfigurations(java.util.List<RunConfiguration> configurations) {
        this.configurations.clear();
        this.configurations.addAll(configurations);
    }

    private RunConfiguration getCurrentConfiguration() {
        return (RunConfiguration) currentConfiguration.getValue();
    }
    
    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }
    protected DataBindingContext initDataBindings() {
        DataBindingContext bindingContext = new DataBindingContext();
        //
        ViewerSupport.bind(runConfigurationsListViewer, configurations, PojoProperties.values(RunConfiguration.class, new String[]{"configurationName"}));
        //
        IObservableValue observeSingleSelectionRunConfigurationsListViewer = ViewerProperties.singleSelection().observe(runConfigurationsListViewer);
        IObservableValue listViewerConfigurationNameObserveDetailValue = PojoProperties.value(RunConfiguration.class, "configurationName", String.class).observeDetail(observeSingleSelectionRunConfigurationsListViewer);
        IObservableValue observeTextNameTxtObserveWidget = WidgetProperties.text(SWT.Modify).observe(nameTxt);
        bindingContext.bindValue(listViewerConfigurationNameObserveDetailValue, observeTextNameTxtObserveWidget, new UpdateValueStrategy(UpdateValueStrategy.POLICY_CONVERT), null);
        //
        IObservableValue observeSingleSelectionRunConfigurationsListViewer_1 = ViewerProperties.singleSelection().observe(runConfigurationsListViewer);
        IObservableValue runConfigurationsListViewerConnectionStringObserveDetailValue = PojoProperties.value(RunConfiguration.class, "connectionString", String.class).observeDetail(observeSingleSelectionRunConfigurationsListViewer_1);
        IObservableValue observeTextConnectionStringTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(connectionStringText);
        bindingContext.bindValue(runConfigurationsListViewerConnectionStringObserveDetailValue, observeTextConnectionStringTextObserveWidget, null, new UpdateValueStrategy(UpdateValueStrategy.POLICY_CONVERT));
        //
        IObservableValue observeSingleSelectionRunConfigurationsListViewer_2 = ViewerProperties.singleSelection().observe(runConfigurationsListViewer);
        IObservableValue runConfigurationsListViewerDriverClassNameObserveDetailValue = PojoProperties.value(RunConfiguration.class, "driverClassName", String.class).observeDetail(observeSingleSelectionRunConfigurationsListViewer_2);
        IObservableValue observeTextDriverClassNameTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(driverClassNameText);
        bindingContext.bindValue(runConfigurationsListViewerDriverClassNameObserveDetailValue, observeTextDriverClassNameTextObserveWidget, null, new UpdateValueStrategy(UpdateValueStrategy.POLICY_CONVERT));
        //
        IObservableValue observeSingleSelectionRunConfigurationsListViewer_3 = ViewerProperties.singleSelection().observe(runConfigurationsListViewer);
        IObservableValue runConfigurationsListViewerUserNameObserveDetailValue = PojoProperties.value(RunConfiguration.class, "userName", String.class).observeDetail(observeSingleSelectionRunConfigurationsListViewer_3);
        IObservableValue observeTextUsernameTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(usernameText);
        bindingContext.bindValue(runConfigurationsListViewerUserNameObserveDetailValue, observeTextUsernameTextObserveWidget, null, new UpdateValueStrategy(UpdateValueStrategy.POLICY_CONVERT));
        //
        IObservableValue observeSingleSelectionRunConfigurationsListViewer_4 = ViewerProperties.singleSelection().observe(runConfigurationsListViewer);
        IObservableValue runConfigurationsListViewerPasswordObserveDetailValue = PojoProperties.value(RunConfiguration.class, "password", String.class).observeDetail(observeSingleSelectionRunConfigurationsListViewer_4);
        IObservableValue observeTextPasswordTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(passwordText);
        bindingContext.bindValue(runConfigurationsListViewerPasswordObserveDetailValue, observeTextPasswordTextObserveWidget, null, new UpdateValueStrategy(UpdateValueStrategy.POLICY_CONVERT));
        //
        IObservableValue observeSingleSelectionRunConfigurationsListViewer_5 = ViewerProperties.singleSelection().observe(runConfigurationsListViewer);
        IObservableValue runConfigurationsListViewerChangeLogObserveDetailValue = PojoProperties.value(RunConfiguration.class, "changeLog", File.class).observeDetail(observeSingleSelectionRunConfigurationsListViewer_5);
        IObservableValue observeTextChangeLogFilePathTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(changeLogFilePathText);
        UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setConverter(new FileToStringConverter());
        UpdateValueStrategy strategy_1 = new UpdateValueStrategy(UpdateValueStrategy.POLICY_CONVERT);
        strategy_1.setConverter(new StringToFileConverter());
        bindingContext.bindValue(runConfigurationsListViewerChangeLogObserveDetailValue, observeTextChangeLogFilePathTextObserveWidget, strategy, strategy_1);
        //
        //
        return bindingContext;
    }

    @SuppressWarnings("unchecked")
    private void applyPressed(final Button btnApply) {
        m_bindingContext.updateTargets();
        java.util.List<ChangeLogParameter> changeLogParameters = getCurrentConfiguration().getChangeLogParameters();
        changeLogParameters.clear();
        changeLogParameters.addAll(currentChangeLogParameters);
        btnApply.setEnabled(false);
    }

    private void revertPressed() {
        m_bindingContext.updateModels();
        fillChangeLogParametersFromCurrentConfig();
    }

    private void fillChangeLogParametersFromCurrentConfig() {
        currentChangeLogParameters.clear();
        Object value = currentConfiguration.getValue();
        if (value instanceof RunConfiguration) {
            for (ChangeLogParameter parameter : ((RunConfiguration)value).getChangeLogParameters()) {
                currentChangeLogParameters.add(parameter.copy());
            }
        }
    }

    private ChangeLogParameter getSelectedChangeLogParameter() {
        IStructuredSelection selection = (IStructuredSelection) changeLogParametersTableViewer.getSelection();
        ChangeLogParameter parameter = (ChangeLogParameter) selection.getFirstElement();
        return parameter;
    }
}
