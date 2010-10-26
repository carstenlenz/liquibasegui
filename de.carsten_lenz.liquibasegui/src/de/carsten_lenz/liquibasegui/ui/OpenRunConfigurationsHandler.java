package de.carsten_lenz.liquibasegui.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.ui.handlers.HandlerUtil;

import de.carsten_lenz.liquibasegui.model.ChangeLogParameter;
import de.carsten_lenz.liquibasegui.model.RunConfiguration;


public class OpenRunConfigurationsHandler extends AbstractHandler {

    public Object execute(ExecutionEvent event) {
        
        RunConfigurationsDialog runConfigsDialog = new RunConfigurationsDialog(HandlerUtil.getActiveShell(event));
        
        RunConfiguration config1 = new RunConfiguration();
        config1.setConfigurationName("Config1");
        config1.setConnectionString("jdbc://blabla");
        config1.setUserName("hamsdi");
        
        List<ChangeLogParameter> params1 = new ArrayList<ChangeLogParameter>();
        params1.add(new ChangeLogParameter("env", "dev"));
        params1.add(new ChangeLogParameter("hulla", "hu"));
        config1.getChangeLogParameters().addAll(params1);
        
        RunConfiguration config2 = new RunConfiguration();
        config2.setConfigurationName("config2");
        config2.setConnectionString("jdbc://huibuh");
        config2.setUserName("bamsdi");
        
        List<RunConfiguration> configs = new ArrayList<RunConfiguration>();
        configs.add(config1);
        configs.add(config2);
        
        runConfigsDialog.setRunConfigurations(configs);
        
        runConfigsDialog.open();
        
        return null;
    }
}