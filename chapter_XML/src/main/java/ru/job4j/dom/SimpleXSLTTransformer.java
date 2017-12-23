package ru.job4j.dom;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class SimpleXSLTTransformer {

    /**
     * Метод наложения стиля из файла XSL на XML файл
     * @param xml исходный xml файл
     * @param xsl файл стиля
     * @param result итоговый xml файл
     */
    public void xmlTransform(String xml, String xsl, String result) {
        try {
            StreamSource inputXML = new StreamSource(new File(xml));
            StreamSource inputXSL = new StreamSource(new File(xsl));
            StreamResult outputXML = new StreamResult(new File(result));

            Transformer transformer = TransformerFactory.newInstance().newTransformer(inputXSL);
            transformer.transform(inputXML, outputXML);

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleXSLTTransformer t = new SimpleXSLTTransformer();
        String xml = "chapter_XML\\src\\main\\resources\\requests.xml";
        String xsl = "chapter_XML\\src\\main\\resources\\requestsStylesheet.xsl";
        String result = "chapter_XML\\src\\main\\resources\\result.xml";
        t.xmlTransform(xml, xsl, result);
    }
}
