package com.chudnikau.vowels;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 *  The class represents result of average calculated vowels
 */

/**
 * @author Siarhei Chudnikau
 * @created 13/02/2023
 * @project vowels
 */
public class VowelsResult extends LinkedHashMap<String, Double> {

    public static final String lineSeparator = System.getProperty("line.separator");

    private final DecimalFormat decimalFormat;

    VowelsResult() {
        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator('.');

        // Formatting output decimal as 2.5->2.5, 2.0->2
        decimalFormat = new DecimalFormat("0.#", decimalFormatSymbols);
    }

    public void writeResult(OutputStream outputStream) throws IOException {
        outputStream.write(
                buildResult().getBytes());
    }

    /*
     * Builds calculated result as string
     * Ex:
     * ({a, o}, 6) -> 2.5
     * ({a, o}, 5) -> 2
     * ({a, e}, 4) -> 2
     */
    private String buildResult() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, Double> entry : entrySet()) {
            stringBuilder
                    .append(entry.getKey().toLowerCase())
                    .append(" -> ")
                    .append(decimalFormat.format(entry.getValue()))
                    .append(lineSeparator);
        }

        //  trim() removes the latest added lineSeparator
        return stringBuilder.toString().trim();
    }

    @Override
    public String toString() {
        return buildResult();
    }
}
