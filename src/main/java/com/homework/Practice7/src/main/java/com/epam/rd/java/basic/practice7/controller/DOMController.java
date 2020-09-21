package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.entity.Bank;
import com.epam.rd.java.basic.practice7.entity.Deposit;
import com.epam.rd.java.basic.practice7.util.Sorter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DOMController {
    private String xmlFileName;
    private static final DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();
    private static final TransformerFactory TRANSFORMER_FACTORY = TransformerFactory.newInstance();

    private Bank bank;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Bank getBank() {
        return bank;
    }

    public void parse()
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder;
        DOCUMENT_BUILDER_FACTORY.setNamespaceAware(true);
        // turn validation on
        DOCUMENT_BUILDER_FACTORY.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
        // turn on xsd validation
        DOCUMENT_BUILDER_FACTORY.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        documentBuilder = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();

        Document document = documentBuilder.parse(xmlFileName);
        Element root = document.getDocumentElement();

        bank = new Bank();

        NodeList depositNodes = root.getElementsByTagName("Deposit");
        for (int i = 0; i < depositNodes.getLength(); i++) {
            Element depositElement = (Element) depositNodes.item(i);
            Deposit deposit = getDeposit(depositElement);
            bank.getDeposits().add(deposit);
        }
    }

    private Deposit getDeposit(Element depositElement) {
        Deposit deposit = new Deposit();
        deposit.setType(Deposit.Type.valueOf(depositElement.getAttribute("type")));
        deposit.setAccountId(depositElement.getAttribute("ID"));
        deposit.setBankName(depositElement.getElementsByTagName("Name").item(0).getTextContent());
        deposit.setCountry(depositElement.getElementsByTagName("Country").item(0).getTextContent());
        deposit.setDepositor(depositElement.getElementsByTagName("Depositor").item(0).getTextContent());
        deposit.setAmountOnDeposit(Long.parseLong(depositElement.getElementsByTagName("Amount").item(0).getTextContent()));
        deposit.setProfitability(Integer.parseInt(depositElement.getElementsByTagName("percent").item(0).getTextContent()));
        deposit.setTimeConstraints(Integer.parseInt(depositElement.getElementsByTagName("time").item(0).getTextContent()));

        return deposit;
    }

    private static Document getDocument(Bank bank) throws ParserConfigurationException {
        DOCUMENT_BUILDER_FACTORY.setNamespaceAware(true);
        DocumentBuilder documentBuilder = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rElement = document.createElement("Bank");
        document.appendChild(rElement);

        for (Deposit deposit : bank.getDeposits()) {
            Element depositElement = document.createElement("Deposit");

            depositElement.setAttribute("type", deposit.getType().name());
            depositElement.setAttribute("ID", deposit.getAccountId());

            Element nameElement = document.createElement("Name");
            nameElement.setTextContent(deposit.getBankName());
            depositElement.appendChild(nameElement);

            Element countryElement = document.createElement("Country");
            countryElement.setTextContent(deposit.getCountry());
            depositElement.appendChild(countryElement);

            Element depositorElement = document.createElement("Depositor");
            depositorElement.setTextContent(deposit.getDepositor());
            depositElement.appendChild(depositorElement);

            Element amountElement = document.createElement("Amount");
            amountElement.setTextContent(deposit.getAmountOnDeposit().toString());
            depositElement.appendChild(amountElement);

            Element percentElement = document.createElement("percent");
            percentElement.setTextContent(deposit.getProfitability().toString());
            depositElement.appendChild(percentElement);

            Element timeElement = document.createElement("time");
            timeElement.setTextContent(deposit.getTimeConstraints().toString());
            depositElement.appendChild(timeElement);

            rElement.appendChild(depositElement);
        }
        return document;
    }

    public static void saveToXML(Bank bank, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        saveToXML(getDocument(bank), xmlFileName);
    }

    private static void saveToXML(Document document, String xmlFileName)
            throws TransformerException {

        StreamResult result = new StreamResult(new File(xmlFileName));

        // set up transformation
        Transformer t = TRANSFORMER_FACTORY.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        // run transformation
        t.transform(new DOMSource(document), result);
    }

    public static void main(final String[] args)
            throws IOException, SAXException, ParserConfigurationException, TransformerException {
        if (args.length == 0) return;

        String fileName = args[0];
        String xmlFileName = Constants.VALID_XML_OUTPUT_DOM_FILE;
        DOMController domController = new DOMController(fileName);
        domController.parse();

        List<Deposit> list = domController.getBank().getDeposits();

        list.forEach(System.out::println);
        System.out.println();

        list.sort(Sorter.SORT_DEPOSITS_BY_TERM);
        list.forEach(System.out::println);
        System.out.println();

        list.sort(Sorter.SORT_DEPOSITS_BY_AMOUNT);
        list.forEach(System.out::println);
        System.out.println();

        list.sort(Sorter.SORT_DEPOSITS_BY_BANK_NAME);
        list.forEach(System.out::println);

        DOMController.saveToXML(domController.getBank(), xmlFileName);
    }
}
