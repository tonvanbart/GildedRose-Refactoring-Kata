package com.gildedrose;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by ton on 30/11/16.
 */
public class TextTest {

    ByteArrayOutputStream testOutput;

    @Before
    public void createFixture() {
        testOutput = new ByteArrayOutputStream();
    }

    @Test
    public void testTextOutput() throws Exception {
        System.setOut(new PrintStream(testOutput));
        TexttestFixture.main(new String[] { "20" });

        BufferedReader testReader = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(testOutput.toByteArray())));

        BufferedReader masterReader = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/goldenmaster.txt")));

        List<String> actual = testReader.lines().collect(Collectors.toList());
        List<String> expected = masterReader.lines().collect(Collectors.toList());

        assertEquals("Output should be unchanged", expected, actual);
    }
}
