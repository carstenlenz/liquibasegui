package de.carsten_lenz.liquibasegui.ui

import scalaj.collection.Imports._
import org.eclipse.core.commands._
import de.carsten_lenz.liquibasegui.model._

class OpenRunConfigurationsHandler extends AbstractHandler with HandlerHelpers {

    def execute(e: ExecutionEvent): Object = {
        implicit val event = e; // enables implicits on HandlerHelpers
        
        val runConfigsDialog = new RunConfigurationsDialog(activeShell)
        
        val config1 = new RunConfiguration()
        config1.setConfigurationName("Config1")
        config1.setConnectionString("jdbc://blabla")
        config1.setUserName("hamsdi")
        
        val config2 = new RunConfiguration
        config2.setConfigurationName("config2")
        config2.setConnectionString("jdbc://huibuh")
        config2.setUserName("bamsdi")
        
        val configs = List(config1, config2)
        
        runConfigsDialog.setRunConfigurations(configs.asJava)
        
        runConfigsDialog.open
        
        null
    }
}