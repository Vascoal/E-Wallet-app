package com.example.projectvasco;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Utils {
    public static String rupiah(long value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("in", "ID"));
        DecimalFormat df = new DecimalFormat("#,###", symbols);
        return "Rp " + df.format(value);
    }
}