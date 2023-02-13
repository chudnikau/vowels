package com.chudnikau.vowels;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.chudnikau.vowels.VowelsResult.lineSeparator;

/*
 * The class determines the average number of vowels per word grouped by:
 * set of vowels presented in a word and length of the word.
 */

/**
 * @author Siarhei Chudnikau
 * @created 13/02/2023
 * @project vowels
 */
public class VowelsCalculationTest {

    private byte[] calcTest(String value) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(value.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        new VowelsCalculation()
                .readVowels(inputStream)
                .calcAverage()
                .writeResult(outputStream);
        return outputStream.toByteArray();
    }

    @Test
    public void emptyLineTest() throws IOException {
        String expected = "";
        Assert.assertArrayEquals(expected.getBytes(),
                calcTest(""));
    }

    @Test
    public void noWhiteSpacesTest() throws IOException {
        String expected = "({a, o}, 6) -> 2.5" + lineSeparator +
                "({a, o}, 5) -> 2" + lineSeparator +
                "({a, e}, 4) -> 2";
        Assert.assertArrayEquals(expected.getBytes(),
                calcTest("platon made bamboo boats."));
    }


    @Test
    public void whiteSpacesTest() throws IOException {
        String expected = "({a, o}, 6) -> 2.5" + lineSeparator +
                "({a, o}, 5) -> 2" + lineSeparator +
                "({a, e}, 4) -> 2";
        Assert.assertArrayEquals(expected.getBytes(),
                calcTest("   platon,\t\nmade\rbamboo  boats\t."));
    }


    @Test
    public void lowerCaseTest() throws IOException {
        String expected = "({a, o}, 6) -> 2.5" + lineSeparator +
                "({a, o}, 5) -> 2" + lineSeparator +
                "({a, e}, 4) -> 2";
        Assert.assertArrayEquals(expected.getBytes(),
                calcTest("   platon,\t\nmade\rbamboo  boats\t."));
    }

    @Test
    public void upperCaseTest() throws IOException {
        String expected = "({a, o}, 6) -> 2.5" + lineSeparator +
                "({a, o}, 5) -> 2" + lineSeparator +
                "({a, e}, 4) -> 2";
        Assert.assertArrayEquals(expected.getBytes(),
                calcTest("   platon,\t\nmade\rbamboo  boats\t.".toUpperCase()));
    }

    @Test
    public void upperAndLowerCaseTest() throws IOException {
        String expected = "({a, o}, 6) -> 2.5" + lineSeparator +
                "({a, o}, 5) -> 2" + lineSeparator +
                "({a, e}, 4) -> 2";
        Assert.assertArrayEquals(expected.getBytes(),
                calcTest("   PlAtOn,\t\nmade\rbAmboO  boAts\t."));
    }

    @Test
    public void noVowelsTest() throws IOException {
        String expected = "";
        Assert.assertArrayEquals(expected.getBytes(),
                calcTest("s   pltn,\t" + "md\n" + "bmbbb  btsts"));
    }

    @Test
    public void onlyVowelsTest() throws IOException {
        String expected = "({a, i, o}, 6) -> 6" + lineSeparator +
                "({o}, 4) -> 4";
        Assert.assertArrayEquals(expected.getBytes(),
                calcTest("aaooii \t\t\t  oooo"));
    }

    @Test
    public void notLettersTest() throws IOException {
        String expected = "";
        Assert.assertArrayEquals(expected.getBytes(),
                calcTest("!@.*\t% *!"));
    }

    @Test
    public void commaTest() throws IOException {
        String expected = "({a, o}, 6) -> 6";
        Assert.assertArrayEquals(expected.getBytes(),
                calcTest("aaa,ooo"));
    }

    @Test
    public void punctuationWhiteSpacesTest() throws IOException {
        String expected = "({a, o}, 6) -> 6" + lineSeparator +
                "({a, o}, 5) -> 2" + lineSeparator +
                "({a, e}, 4) -> 2";
        Assert.assertArrayEquals(expected.getBytes(),
                calcTest("aaa,ooo\tpla-on   \nma!de\t boats"));
    }
}
