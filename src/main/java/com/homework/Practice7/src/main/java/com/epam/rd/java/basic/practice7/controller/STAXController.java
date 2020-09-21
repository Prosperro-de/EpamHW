package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.XML;
import com.epam.rd.java.basic.practice7.entity.Bank;
import com.epam.rd.java.basic.practice7.entity.Deposit;
import com.epam.rd.java.basic.practice7.util.Sorter;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.util.List;

public class STAXController extends DefaultHandler {
    private String xmlFileName;
    private Bank bank;
    private Deposit deposit;
    private String currentElement;
    private static final XMLInputFactory XML_INPUT_FACTORY = XMLInputFactory.newFactory();

    public Bank getBank() {
        return bank;
    }

    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse() throws XMLStreamException {
        XML_INPUT_FACTORY.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        XMLEventReader reader = XML_INPUT_FACTORY.createXMLEventReader(
                new StreamSource(xmlFileName));
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (!(event.isCharacters() && event.asCharacters().isWhiteSpace())) {
                if (event.isStartElement()) {
                    processStartElement(event);
                }

                if (event.isCharacters()) {
                    setParameters(event);
                }

                if (event.isEndElement()) {
                    processEndElement(event);
                }
            }
        }
        reader.close();
    }

    private void setParameters(XMLEvent event) {
        Characters characters = event.asCharacters();

        if (XML.NAME.equalsTo(currentElement)) {
            deposit.setBankName(characters.getData());
        }
        if (XML.COUNTRY.equalsTo(currentElement)) {
            deposit.setCountry(characters.getData());
        }
        if (XML.DEPOSITOR.equalsTo(currentElement)) {
            deposit.setDepositor(characters.getData());
        }
        if (XML.AMOUNT.equalsTo(currentElement)) {
            deposit.setAmountOnDeposit(Long.parseLong(characters.getData()));
        }
        if (XML.PERCENT.equalsTo(currentElement)) {
            deposit.setProfitability(Integer.parseInt(characters.getData()));
        }
        if (XML.TIME.equalsTo(currentElement)) {
            deposit.setTimeConstraints(Integer.parseInt(characters.getData()));
        }
    }

    private void processEndElement(XMLEvent event) {
        EndElement endElement = event.asEndElement();
        String localName = endElement.getName().getLocalPart();
        if (XML.DEPOSIT.equalsTo(localName)) {
            bank.getDeposits().add(deposit);
        }
    }

    private void processStartElement(XMLEvent event) {
        StartElement startElement = event.asStartElement();
        currentElement = startElement.getName().getLocalPart();

        if (XML.BANK.equalsTo(currentElement)) {
            bank = new Bank();
            return;
        }

        if (XML.DEPOSIT.equalsTo(currentElement)) {
            deposit = new Deposit();
            Attribute attrID = startElement.getAttributeByName(new QName(XML.ID.value()));
            Attribute attrType = startElement.getAttributeByName(new QName(XML.TYPE.value()));
            deposit.setAccountId(attrID.getValue());
            deposit.setType(Deposit.Type.valueOf(attrType.getValue()));
        }
    }

    public static void main(String[] args)
            throws XMLStreamException, TransformerException, ParserConfigurationException {
        STAXController staxController = new STAXController(args[0]);
        staxController.parse();

        Bank bank = staxController.getBank();
        List<Deposit> list = bank.getDeposits();
        Sorter.printSortedList(list);

        DOMController.saveToXML(staxController.getBank(), Constants.VALID_XML_OUTPUT_STAX_FILE);
    }
}