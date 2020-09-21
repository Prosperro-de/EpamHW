package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.XML;
import com.epam.rd.java.basic.practice7.entity.Bank;
import com.epam.rd.java.basic.practice7.entity.Deposit;
import com.epam.rd.java.basic.practice7.util.Sorter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class SAXController extends DefaultHandler {
    private String xmlFileName;
    private static final SAXParserFactory SAX_PARSER_FACTORY = SAXParserFactory.newInstance();

    private String currentElement;
    private Deposit currentDeposit;

    private Bank bank;

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Bank getBank() {
        return bank;
    }

    public void parse() throws ParserConfigurationException, IOException, SAXException {
        SAX_PARSER_FACTORY.setNamespaceAware(true);
        SAX_PARSER_FACTORY.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
        SAX_PARSER_FACTORY.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        SAXParser parser = SAX_PARSER_FACTORY.newSAXParser();
        parser.parse(xmlFileName, this);
    }

    @Override
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
        // if XML document not valid just throw exception
        throw e;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentElement = localName;
        if (XML.BANK.equalsTo(localName)) {
            bank = new Bank();
        }

        if (XML.DEPOSIT.equalsTo(localName)) {
            if (bank == null) {
                return;
            }
            currentDeposit = new Deposit();
            currentDeposit.setAccountId(attributes.getValue(0));
            currentDeposit.setType(Deposit.Type.valueOf(attributes.getValue(1)));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String elementText = new String(ch, start, length).trim();
        if (elementText.isEmpty()) {
            return;
        }
        if (XML.NAME.equalsTo(currentElement)) {
            currentDeposit.setBankName(elementText);
        }
        if (XML.COUNTRY.equalsTo(currentElement)) {
            currentDeposit.setCountry(elementText);
        }
        if (XML.DEPOSITOR.equalsTo(currentElement)) {
            currentDeposit.setDepositor(elementText);
        }
        if (XML.AMOUNT.equalsTo(currentElement)) {
            currentDeposit.setAmountOnDeposit(Long.parseLong(elementText));
        }
        if (XML.PERCENT.equalsTo(currentElement)) {
            currentDeposit.setProfitability(Integer.parseInt(elementText));
        }
        if (XML.TIME.equalsTo(currentElement)) {
            currentDeposit.setTimeConstraints(Integer.parseInt(elementText));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (XML.DEPOSIT.equalsTo(localName)) {
            bank.getDeposits().add(currentDeposit);
        }
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        SAXController saxController = new SAXController(args[0]);
        saxController.parse();

        List<Deposit> list = saxController.getBank().getDeposits();
        Sorter.printSortedList(list);

        DOMController.saveToXML(saxController.getBank(), Constants.VALID_XML_OUTPUT_SAX_FILE);
    }
}

