package ru.job4j.dom;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleStAXUser {

    /**
     * Метод чтания из XML и отображения в консоли
     * @param file адрес файла
     */
    public void readXML(String file) {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(file));
            //входим в основной блок
            reader.next();

            while (reader.hasNext()) {
                reader.next();
                //если начинается блок каждой отдельной заявки
                if (reader.isStartElement() && reader.getLocalName().equals("request")) {
                    System.out.println("Request content: ");

                //выводим наименование атрибута
                } else if (reader.isStartElement()) {
                    System.out.print(reader.getLocalName() + ": ");

                //выводим содержание атрибута
                } else if (reader.hasText() && reader.getText().trim().length() > 1) {
                    System.out.println(reader.getText());

                } else if (reader.isEndElement() && reader.getLocalName().equals("request")) {
                    System.out.println("<===========================>");
                }
            }


        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создания списка каждой заявки из XML документа
     * @param file адрес файла
     * @return список заявок
     */
    public List<Request> parseXMLtoList(String file) {
        List<Request> requests = new ArrayList<>();
        Request request = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream((file)));

            //последовательно перебираем все элементы на предмет совпадения по наименованию атрибута
            while (reader.hasNext()) {
                reader.next();
                if (reader.isStartElement() && reader.getLocalName().equals("request")) {
                    //создаем новую заявку
                    request = new Request();

                } else if (reader.isStartElement() && reader.getLocalName().equals("name")) {
                    reader.next();
                    request.setName(reader.getText());

                } else if (reader.isStartElement() && reader.getLocalName().equals("surname")) {
                    reader.next();
                    request.setSurname(reader.getText());

                } else if (reader.isStartElement() && reader.getLocalName().equals("date")) {
                    reader.next();
                    request.setDate(reader.getText());

                } else if (reader.isStartElement() && reader.getLocalName().equals("title")) {
                    reader.next();
                    request.setTitle(reader.getText());

                } else if (reader.isStartElement() && reader.getLocalName().equals("text")) {
                    reader.next();
                    request.setText(reader.getText());

                } else if (reader.isEndElement() && reader.getLocalName().equals("request")) {
                    //добавляем заявку в список
                    requests.add(request);
                }

            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

        return requests;
    }


    public void writeXML(String file) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(file));

            writer.writeStartDocument("1.0");

            writer.writeStartElement("requests");

                writer.writeStartElement("request");

                    writer.writeStartElement("name");
                    writer.writeCharacters("Bianka");
                    writer.writeEndElement();

                    writer.writeStartElement("surname");
                    writer.writeCharacters("Pollock");
                    writer.writeEndElement();

                    writer.writeStartElement("date");
                    writer.writeCharacters("19.12.2017");
                    writer.writeEndElement();

                    writer.writeStartElement("title");
                    writer.writeCharacters("Technical support");
                    writer.writeEndElement();

                    writer.writeStartElement("text");
                    writer.writeCharacters("May I ask for technician to come?");
                    writer.writeEndElement();

                writer.writeEndElement();

            writer.writeEndElement();

            writer.writeEndDocument();

            writer.flush();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SimpleStAXUser stax = new SimpleStAXUser();

        String file = "chapter_XML\\src\\main\\resources\\requests.xml";

        //парсинг в список и вывод всех внесенных заявок
        List<Request> list = stax.parseXMLtoList(file);
        for (Request r : list) {
            System.out.println(r);
        }

        String fileCreated = "chapter_XML\\src\\main\\resources\\requestsCreated.xml";

        //добавление элемента в XML документ
        stax.writeXML(fileCreated);

        //чтение XML и вывод в консоль
        stax.readXML(fileCreated);
    }
}
