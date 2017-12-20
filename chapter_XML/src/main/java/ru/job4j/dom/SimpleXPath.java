package ru.job4j.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

/**
 * Класс поиска данных в XML файле.
 * XML документ - список клиентов.
 *
 * ЗАДАНИЕ:
 * Найти клиента по id 3
 * Найти клиентов возрастом до 18 лет
 * Найти клиентов из города Москва
 */
public class SimpleXPath {

    String file = "chapter_XML\\src\\main\\resources\\clients.xml";

    /**
     * Метод поиска клиента по id
     * @param doc документ
     * @param id id для поиска
     */
    public void findClientById(Document doc, int id) {
        XPathFactory xfactory = XPathFactory.newInstance();
        XPath xpath = xfactory.newXPath();
        try {
            XPathExpression expression = xpath.compile("clients/client[@id = '" + id + "']/name/text()");
            String name = (String) expression.evaluate(doc, XPathConstants.STRING);
            System.out.println(String.format("У клиента с id %s имя %s", id, name));
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод поиска клиента по возрасту
     * @param doc документ
     * @param min минимальный возраст в диапазоне поиска
     * @param max максимальный возраст в диапазоне поиска
     */
    public void findClientsByAge(Document doc, int min, int max) {
        XPathFactory xfactory = XPathFactory.newInstance();
        XPath xpath = xfactory.newXPath();

        try {
            XPathExpression expression = xpath.compile("clients/client[" + min + " < age < " + max + "]/name/text()");
            NodeList list = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
            System.out.println(String.format("В возрасте от %s до %s:", min, max));
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                System.out.println(node.getTextContent());
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод поиска клиента по городу
     * @param doc додкумент
     * @param city город для поиска
     */
    public void findClientByCity(Document doc, String city) {
        XPathFactory xfactory = XPathFactory.newInstance();
        XPath xpath = xfactory.newXPath();

        try {
            XPathExpression expression = xpath.compile("clients/client[city = '" + city + "']/name/text()");
            NodeList list = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
            System.out.println(String.format("В городе %s проживает:", city));
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                System.out.println(node.getTextContent());
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String file = "chapter_XML\\src\\main\\resources\\clients.xml";
        DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();

        SimpleXPath sxpath = new SimpleXPath();

        try {
            DocumentBuilder dbuilder = dfactory.newDocumentBuilder();
            Document document = dbuilder.parse(file);

            sxpath.findClientById(document, 3);
            sxpath.findClientsByAge(document, 0, 18);
            sxpath.findClientByCity(document, "Moscow");

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}
