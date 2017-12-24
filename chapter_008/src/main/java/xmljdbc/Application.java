package xmljdbc;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.*;
import java.io.*;
import java.sql.*;

/**
 * Класс работы с Базой данных и XML документами.
 * Задание:
 * - создать таблицу в БД, если отсутствует
 * - вставить в таблицу значения 1...number
 * - сформировать XML документ вида
 *   <entries>
 *   <entry>
 *   <field>значение поля field</field>
 *   </entry>
 *    ...
 *   <entry>
 *   <field>значение поля field</field>
 *   </entry>
 *   </entries>
 * сохранить документ под именем 1.xml
 * - посредством XSLT преобразовать документ к виду
 * <entries>
 * <entry field="значение поля field">
 * ...
 * <entry field="значение поля field">
 * </entries>
 * сохранить документ под именем 2.xml
 * - парсить 2.xml  и вывести сумму значений всех атрибутов
 */

public class Application {

    private String user;
    private String pass;
    private int number;

    /**
     * Устанавливаем значения для подключения БД.
     * @param user пользователь
     * @param pass пароль
     */
    public void setCredentials(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    /**
     * Устанавливаем значение количества записей в БД.
     * @param number количество записей
     */
    public void setNumber(int number) {
        this.number = number;
    }

    public void init() {
        Connection conn = null;
        try {
            conn = this.connectDB();

            //создание таблицы
            this.createTable(conn);

            //заполнение таблицы данными
            this.insertValues(conn);

            //создание xml документа с данными БД
            this.makeXML(conn);

            //изменение стиля XML с использованием XSLT
            this.transformXML();

            //парсинг документа с использованием StAX
            this.parseXMLbyStAX();

            //парсинг документа с использованием xPath
            this.parseXMLbyXPath();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Метод подключения к базе данных.
     */
    private Connection connectDB() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/numbers_storage";
        return DriverManager.getConnection(url, this.user, this.pass);
    }

    /**
     * Метод создания таблицы.
     * @param conn подключение к базе данных
     */
    private void createTable(Connection conn) {
        Statement st = null;

        try {
            st = conn.createStatement();
            String table = "CREATE TABLE IF NOT EXISTS Numbers ("
                    + "id SERIAL PRIMARY KEY,"
                    + "num int NOT NULL"
                    + ");"
                    + "DELETE FROM Numbers";
            st.executeUpdate(table);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Метод заполнения таблицы данными.
     * @param conn подключение к базе данных
     */
    private void insertValues(Connection conn) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("INSERT INTO Numbers (num) VALUES (?)");
            for (int i = 1; i <= this.number; i++) {
                pst.setInt(1, i);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Метод создания XML файла из данных БД.
     * @param conn подключение базы данных
     */
    public void makeXML(Connection conn) {
        String file = "chapter_008\\src\\main\\resources\\1.xml";
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        Statement st = null;

        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Numbers");

            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(file));
            writer.writeStartDocument("1.0");
                writer.writeStartElement("entries");
                    while (rs.next()) {
                        writer.writeStartElement("entry");
                            writer.writeStartElement("field");
                            writer.writeCharacters(Integer.toString(rs.getInt("num")));
                            writer.writeEndElement();
                        writer.writeEndElement();
                    }
                    writer.writeEndElement();
                writer.writeEndDocument();
            writer.flush();

            rs.close();

        } catch (SQLException | XMLStreamException | IOException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Метод преобразования XML документа с использованием XSLT.
     */
    public void transformXML() {
        String xml = "chapter_008\\src\\main\\resources\\1.xml";
        String xsl = "chapter_008\\src\\main\\resources\\stylesheet.xsl";
        String result = "chapter_008\\src\\main\\resources\\2.xml";

        StreamSource inputXML = new StreamSource(new File(xml));
        StreamSource inputXSL = new StreamSource(new File(xsl));
        StreamResult outputXML = new StreamResult(new File(result));

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(inputXSL);
            transformer.transform(inputXML, outputXML);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод парсинга xml документа с использованием StAX.
     */
    private void parseXMLbyStAX() {
        String file = "chapter_008\\src\\main\\resources\\2.xml";

        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(file));

            int sum = 0;
            while (reader.hasNext()) {
                reader.next();
                if (reader.isStartElement() && reader.getLocalName().equals("entry")) {
                    sum = sum + Integer.valueOf(reader.getAttributeValue(0));
                }
            }
            System.out.println(String.format("StAX: Сумма всех значений %s", sum));

        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод парсинга xml документа с использованием xPath.
     */
    private void parseXMLbyXPath() {
        String file = "chapter_008\\src\\main\\resources\\2.xml";

        try {
        DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbuilder = dfactory.newDocumentBuilder();
        Document doc = dbuilder.parse(file);

        XPathFactory xfactory = XPathFactory.newInstance();
        XPath xpath = xfactory.newXPath();
        XPathExpression expression = xpath.compile("//entries/entry/@field");

        NodeList list = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
        int sum = 0;
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            sum += Integer.valueOf(node.getTextContent());
        }
        System.out.println(String.format("xPath: Сумма всех значений %s", sum));

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.setCredentials("postgres", "password");
        app.setNumber(100000);
        app.init();
    }
}
