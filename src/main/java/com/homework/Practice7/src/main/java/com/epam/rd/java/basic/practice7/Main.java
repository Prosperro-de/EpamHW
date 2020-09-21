package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.controller.DOMController;
import com.epam.rd.java.basic.practice7.controller.SAXController;
import com.epam.rd.java.basic.practice7.controller.STAXController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public final class Main {
    private static Logger logger = Logger.getLogger("Main.class");
    private static final String MESSAGE = "Something went wrong:";

    public static void main(String[] args) {
        try {
            DOMController.main(args);
        } catch (SAXException | TransformerException |
                ParserConfigurationException | IOException e) {
            logger.severe(MESSAGE);
            logger.severe(Arrays.toString(e.getStackTrace()));
        }

        try {
            SAXController.main(args);
        } catch (SAXException | TransformerException |
                ParserConfigurationException | IOException e) {
            logger.severe(MESSAGE);
            logger.severe(Arrays.toString(e.getStackTrace()));
        }

        try {
            STAXController.main(args);
        } catch (TransformerException | ParserConfigurationException |
                XMLStreamException e) {
            logger.severe(MESSAGE);
            logger.severe(Arrays.toString(e.getStackTrace()));
        }
    }
}