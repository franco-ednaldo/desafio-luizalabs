package com.example.processfile.util;

import java.text.DecimalFormat;

public class DoubleToStringFormatter {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public static String formatDouble(Double value) {
        return value == null ? null : DECIMAL_FORMAT.format(value);
    }
}
