package de.carste_lenz.liquibasegui.ui

import org.eclipse.core.commands._
import org.eclipse.ui.handlers._

class OpenCommandViewHandler extends AbstractHandler {

  def execute(event: ExecutionEvent): Object = {
      val page = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage;
      page.showView(CommandView.ID)
      
      null
  }

}