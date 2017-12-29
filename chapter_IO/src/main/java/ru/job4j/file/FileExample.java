package ru.job4j.file;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * Практика использования класса File пакета java.io
 */
public class FileExample {

    /**
     * Метод выводит список файлов определенного расширения.
     * @param extensions список расширений. Записываются через запятую
     */
    public void chooseFilesByExtension(String extensions) {

        //выбираем директорию, которую будем проверять на наличие файлов нужного расширения
        File directory = new File("D:\\testDir");

        //создаем фильтр по выбранным расширениям файлов
        FileFilterExample filter = new FileFilterExample(extensions);

        //собираем список файлов, удовлетворяющих условию
        File[] files = directory.listFiles(filter);

        //выводим имена файлов
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

    /**
     * Метод подключения базы данных с параметрами из ресурсного файла.
     */
    public void dbConnection() {
        String pathname = "chapter_IO\\src\\main\\resources\\dbConnection.properties";
        String username = "";
        String password = "";

        //получение параметров подключения БД считыванием из файла
        try {
            FileInputStream fis = new FileInputStream(pathname);
            InputStreamReader reader = new InputStreamReader(fis);

            Properties props = new Properties();
            props.load(reader);
            username = props.getProperty("username");
            password = props.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }


        //подключение БД и вывод результата
        String url = "jdbc:postgresql://localhost:5432/additional_task";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
        Statement statement = conn.createStatement();
        String query = "SELECT name FROM person";
        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            System.out.println(result.getString("name"));
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Метод копирования текста из одного файла в другой.
     */
    public void copyFileText() {

        try {
            FileInputStream fis = new FileInputStream("D:\\testDir\\from.txt");
            FileOutputStream fos = new FileOutputStream("D:\\testDir\\to.txt");

            byte[] buffer = new byte[fis.available()];

            fis.read(buffer, 0, buffer.length);
            fos.write(buffer, 0, buffer.length);

            fis.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод записи в файл с использованием FileWriter.
     */
    public void fileWriter() {

        try (FileWriter writer = new FileWriter("D:\\fileExample.txt", false)) {
            String text = "Текст для записи в файл";
            writer.write(text);

            writer.append("\r\n");

            writer.append("One more line");

            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод чтения из файла с использованием FileReader.
     */
    public void fileReader() {

        try (FileReader reader = new FileReader("D:\\fileExample.txt")) {
            int i;
            while ((i = reader.read()) != -1) {
                System.out.print((char) i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        FileExample fe = new FileExample();

        //fe.chooseFilesByExtension("xml, txt");

        //fe.dbConnection();

        //fe.copyFileText();

        fe.fileWriter();
        fe.fileReader();
    }
}
