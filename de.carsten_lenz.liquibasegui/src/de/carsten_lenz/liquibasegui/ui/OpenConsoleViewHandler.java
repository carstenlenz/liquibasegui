package de.carsten_lenz.liquibasegui.ui;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;


class OpenConsoleViewHandler extends AbstractHandler {

    public Object execute(ExecutionEvent event) {
        MessageConsole messageConsole = new MessageConsole("Test", null);
        ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{ messageConsole});
        
        MessageConsoleStream stream = messageConsole.newMessageStream();
        
        stream.println("Dies ist ein Konsolentest");
        
        return null;
    }
}