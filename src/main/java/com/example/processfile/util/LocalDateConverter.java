package com.example.processfile.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter {

    private LocalDateConverter(){
        throw new IllegalStateException("Utility class");
    }
    public static LocalDate formatStringToLocalDate(final String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        return LocalDate.parse(date, formatter);

    }
}