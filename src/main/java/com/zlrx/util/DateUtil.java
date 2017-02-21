package com.zlrx.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtil {

    private DateUtil() {

    }

    public static LocalDate toLocalDate(Date datum) {
        if (datum == null) return null;
        return datum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Timestamp toDate(LocalDate localDate) {
        if (localDate == null) return null;
        return Timestamp.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
