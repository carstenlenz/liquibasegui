package de.carsten_lenz.liquibasegui.util

import org.eclipse.core.databinding.conversion._
import java.io.File

class StringToFileConverter extends Converter(classOf[String], classOf[File]) {
    def convert(o: java.lang.Object) = o match {
        case s: String if !s.isEmpty => new File(s)
        case _ => null
    }
}
