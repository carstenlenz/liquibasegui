package de.carsten_lenz.liquibasegui.util

import org.eclipse.core.databinding.conversion._
import java.io.File

class FileToStringConverter extends Converter(classOf[File], classOf[String]) {
    def convert(o: Object) = o match {
        case f: File => f.getAbsolutePath
        case _ => ""
    }
}