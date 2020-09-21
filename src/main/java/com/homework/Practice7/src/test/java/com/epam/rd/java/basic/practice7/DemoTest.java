package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.constants.Constants;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DemoTest {
    @Test
    public void mainDemoTest() {
        Demo.main(null);
        boolean fileExists = Files.exists(Paths.get(Constants.VALID_XML_OUTPUT_DOM_FILE));
        Assert.assertTrue(fileExists);
    }

    @After
    public void deleteFiles() {
        try {
            Files.deleteIfExists(Paths.get(Constants.VALID_XML_OUTPUT_DOM_FILE));
            Files.deleteIfExists(Paths.get(Constants.VALID_XML_OUTPUT_SAX_FILE));
            Files.deleteIfExists(Paths.get(Constants.VALID_XML_OUTPUT_STAX_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
