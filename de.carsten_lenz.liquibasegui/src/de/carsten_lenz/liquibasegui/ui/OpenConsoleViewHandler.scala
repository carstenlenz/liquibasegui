package de.carsten_lenz.liquibasegui.ui

import org.eclipse.core.commands._
import org.eclipse.ui.console._

class OpenConsoleViewHandler extends AbstractHandler with HandlerHelpers {

    def execute(e: ExecutionEvent): Object = {
        implicit val event = e; // enables implicits on HandlerHelpers
        
        val messageConsole = new MessageConsole("Test", null)
        ConsolePlugin.getDefault().getConsoleManager().addConsoles(Array[IConsole](messageConsole))
        
        val stream = messageConsole.newMessageStream
        
        stream.println("Dies ist ein Konsolentest")
        
        null
    }
}