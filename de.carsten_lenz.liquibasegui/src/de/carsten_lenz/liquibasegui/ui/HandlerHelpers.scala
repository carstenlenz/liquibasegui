package de.carsten_lenz.liquibasegui.ui

import org.eclipse.core.commands._
import org.eclipse.ui.handlers._
import org.eclipse.ui._
import org.eclipse.swt.widgets._


trait HandlerHelpers {
    def activeWorkbenchWindow(implicit event: ExecutionEvent): IWorkbenchWindow = 
        HandlerUtil.getActiveWorkbenchWindow(event)
    
    def activePage(implicit event: ExecutionEvent): IWorkbenchPage = 
        activeWorkbenchWindow(event).getActivePage
    
    def activeShell(implicit event: ExecutionEvent): Shell = HandlerUtil.getActiveShell(event)
}