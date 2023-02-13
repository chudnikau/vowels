package com.chudnikau.vowels;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Siarhei Chudnikau
 * @created 13/02/2023
 * @project vowels
 */
public class VowelsMain {
    public static void main(String[] args) throws IOException {
        try (
                FileInputStream inputFile =
                        new FileInputStream("data/INPUT.TXT");
                FileOutputStream outputFile =
                        new FileOutputStream("data/OUTPUT.TXT")
        ) {
            new VowelsCalculation()
                    .readVowels(inputFile)
                    .calcAverage()
                    .writeResult(outputFile);
        }
    }
}