package de.carsten_lenz.liquibasegui.util;

import java.io.File;

import org.eclipse.core.databinding.conversion.Converter;

public class FileToStringConverter extends Converter {
    
    public FileToStringConverter() {
        super(File.class, String.class);
    }

    @Override
    public Object convert(Object fromObject) {
        if (fromObject instanceof File) {
            return ((File)fromObject).getAbsolutePath();
        }

        return null;
    }
}
