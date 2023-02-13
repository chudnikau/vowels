package com.chudnikau.vowels;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


/*
 * The class represents the group of set of vowels and word length for map
 * The hashCode consists of:
 *  - set of vowels
 *  - word length
 *
 * The group is sorted by word length in map
 * Sorted groups by word length
 * ({a, o}, 6) -> 2.5  << on top position because word length is 6
 * ({a, o}, 5) -> 2
 * ({a, e}, 4) -> 2
 */

/**
 * @author Siarhei Chudnikau
 * @created 13/02/2023
 * @project vowels
 */
public class VowelsGroup implements Comparable<VowelsGroup> {

    // Sorted vowels
    private final Set<Character> vowels = new TreeSet<>();

    private final Integer wordLength;

    public Integer getWordLength() {
        return wordLength;
    }

    public VowelsGroup(List<Character> vowels, Integer length) {
        this.vowels.addAll(vowels);
        this.wordLength = length;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + wordLength;
        for (Character character : vowels)
            hash = 31 * hash + character.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        VowelsGroup equalObj = (VowelsGroup) obj;
        return vowels.equals(equalObj.vowels) &&
                (Objects.equals(wordLength, equalObj.wordLength));
    }

    /*
     * String representation of the class is ({a, o, i}, 6)
     */
    @Override
    public String toString() {
        return "({" + vowels.stream().map(Object::toString)
                .collect(Collectors.joining(", ")) + "}, " + wordLength + ")";
    }

    @Override
    public int compareTo(VowelsGroup o) {
        return o.wordLength.compareTo(wordLength);
    }
}