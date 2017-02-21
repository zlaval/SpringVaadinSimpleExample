package com.zlrx.util;


import com.vaadin.data.util.converter.Converter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class DateToLocalDateConverter implements Converter<Date, LocalDate> {

    private static final long serialVersionUID = 428403629165927475L;

    @Override
    public LocalDate convertToModel(Date d, Class<? extends LocalDate> aClass, Locale locale) throws ConversionException {
        return DateUtil.toLocalDate(d);
    }

    @Override
    public Date convertToPresentation(LocalDate localDate, Class<? extends Date> aClass, Locale locale) throws ConversionException {
        return DateUtil.toDate(localDate);
    }

    @Override
    public Class<LocalDate> getModelType() {
        return LocalDate.class;
    }

    @Override
    public Class<Date> getPresentationType() {
        return Date.class;
    }
}
