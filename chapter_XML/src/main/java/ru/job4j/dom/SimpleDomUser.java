package ru.job4j.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

public class SimpleDomUser {

    /**
     * Метод чтения XML файла
     * @param file адрес и наименование файла
     */
    private void readXml(String file) {

        try {
            //создается построитель документа
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            //создается дерево dom документа из файла
            Document doc = builder.parse(file);

            //берем корневой элемент <requests>
            Node root = doc.getDocumentElement();

            //берем список всех заявок <request>
            NodeList requests = root.getChildNodes();

            for (int i = 0; i < requests.getLength(); i++) {
                //получаем каждую заявку <request>
                Node request = requests.item(i);

                if (request.getNodeType() != Node.TEXT_NODE) {
                    //получаем пункты заявки <name>, <surname>...
                    NodeList content = request.getChildNodes();

                    for (int j = 0; j < content.getLength(); j++) {
                        //получаем каждый отдельный пункт <name>, <surname>...
                        Node clause = content.item(j);
                        if (clause.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(clause.getNodeName() + " : " + clause.getTextContent());
                        }
                    }
                    System.out.println("------------------------------------------------");
                }
            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleDomUser dr = new SimpleDomUser();
        dr.readXml("chapter_XML\\src\\main\\resources\\requests.xml");
    }

}
