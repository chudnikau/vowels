package com.chudnikau.vowels;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Siarhei Chudnikau
 * @created 13/02/2023
 * @project vowels
 */
public class VowelsCalculation {

    private final Map<VowelsGroup, List<Integer>> vowelMap = new TreeMap<>();

    private boolean isSeparator(int c) {
        return Character.isWhitespace(c);
    }

    public VowelsCalculation readVowels(InputStream input) throws IOException {
        StringBuilder buffer = new StringBuilder();
        int c;
        while ((c = input.read()) != -1) {
            if (isSeparator(c)) {
                addVowels(buffer.toString());
                buffer.setLength(0);
                continue;
            }
            if (Character.isLetter(c)) {
                buffer.append((char) c);
            }
        }
        addVowels(buffer.toString());
        return this;
    }

    private boolean isCharVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }

    private void addVowels(final String value) {
        List<Character> vowelsList = new ArrayList<>();

        for (char c : value.toCharArray())
            if (isCharVowel(c)) {
                vowelsList.add(Character.toLowerCase(c));
            }

        if (vowelsList.size() > 0) {
            VowelsGroup group =
                    new VowelsGroup(vowelsList, value.length());

            List<Integer> v = vowelMap.get(group);

            if (v == null) {
                vowelMap.put(group,
                        new ArrayList<>(List.of(vowelsList.size())));

            } else {
                v.add(vowelsList.size());
            }
        }
    }

    public VowelsResult calcAverage() {
        VowelsResult res = new VowelsResult();

        // Calc avr result
        for (Map.Entry<VowelsGroup, List<Integer>> entry : vowelMap.entrySet()) {
            double avr = 0;
            for (Integer n : entry.getValue()) {
                avr += n;
            }
            res.put(entry.getKey().toString(), avr / entry.getValue().size());
        }

        return res;
    }
}