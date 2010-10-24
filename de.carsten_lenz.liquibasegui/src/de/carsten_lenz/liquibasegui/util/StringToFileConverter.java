package de.carsten_lenz.liquibasegui.util;

import java.io.File;

import org.eclipse.core.databinding.conversion.Converter;

public class StringToFileConverter extends Converter {

    public StringToFileConverter() {
        super(String.class, File.class);
    }

    @Override
    public Object convert(Object fromObject) {
        if (fromObject instanceof String) {
            return new File((String) fromObject);
        }
        return null;
    }
    
}

