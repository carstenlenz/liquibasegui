package de.carsten_lenz.liquibasegui.ui

import org.eclipse.ui.part.ViewPart
import org.eclipse.swt.widgets._
import org.eclipse.swt._
import org.eclipse.swt.layout._
import org.eclipse.jface.viewers._

class CommandView extends ViewPart {
    
    def createPartControl(parent: Composite) = {
        val gridLayout = new GridLayout()
        parent.setLayout(gridLayout)
        val configComboViewer = new ComboViewer(parent, SWT.NONE)
        
        val gd = new GridData(SWT.FILL, SWT.CENTER, true, false)
        configComboViewer.getCombo().setLayoutData(gd)
    }
    
    def setFocus() = {}
}

object CommandView {
    val ID = "de.carsten_lenz.liquibasegui.commandView"
}