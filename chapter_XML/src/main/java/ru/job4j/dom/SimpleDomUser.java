package ru.job4j.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    public void addRequest(String file) {
        try {
            //создается построитель документа
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            //создается дерево dom документа из файла
            Document doc = builder.parse(file);

            //берем корневой элемент <requests>
            Node root = doc.getDocumentElement();

            //создаем каждый элемент
            Element request = doc.createElement("request");

            //если содержится текст, добавляем его
            Element name = doc.createElement("name");
            name.setTextContent("Dillinger");

            Element surname = doc.createElement("surname");
            surname.setTextContent("Bin");

            Element date = doc.createElement("date");
            date.setTextContent("04.08.2017");

            Element title = doc.createElement("title");
            title.setTextContent("Market overview");

            Element text = doc.createElement("text");
            text.setTextContent("Please prepare market overview");

            //добавляем все элементы в заявку
            request.appendChild(name);
            request.appendChild(surname);
            request.appendChild(date);
            request.appendChild(title);
            request.appendChild(text);

            //добавляем заявку в корневой элемент
            root.appendChild(request);

            //метод записи в файл
            writeDocument(doc, file);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод сохранения в файл
     * @param doc документ
     * @param file адрес расположения файла
     */
    private void writeDocument(Document doc, String file) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();

            DOMSource source = new DOMSource(doc);

            FileOutputStream fos = new FileOutputStream(file);
            StreamResult result = new StreamResult(fos);

            tr.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleDomUser dr = new SimpleDomUser();
        String file = "chapter_XML\\src\\main\\resources\\requests.xml";

        dr.addRequest(file);
        dr.readXml(file);
    }
}
