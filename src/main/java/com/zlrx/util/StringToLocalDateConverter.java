package com.zlrx.util;

import com.vaadin.data.util.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    private static final long serialVersionUID = -6568072928565461243L;

    private final DateTimeFormatter dtf;

    public StringToLocalDateConverter(String format) {
        if (format == null || format.isEmpty()) {
            this.dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        } else {
            this.dtf = DateTimeFormatter.ofPattern(format);
        }
    }

    @Override
    public LocalDate convertToModel(String s, Class<? extends LocalDate> aClass, Locale locale) throws ConversionException {
        try {
            return LocalDate.parse(s, dtf);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String convertToPresentation(LocalDate localDate, Class<? extends String> aClass, Locale locale) throws ConversionException {
        try {
            return localDate.format(dtf);
        } catch (NullPointerException e) {
            return "";
        }
    }

    @Override
    public Class<LocalDate> getModelType() {
        return LocalDate.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
