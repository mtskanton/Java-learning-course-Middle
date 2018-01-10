package siteparser;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Properties;

/**
 * Класс базы данных для сохранения вакансий.
 */
public class DataBase {

    private static final Logger LOGGER = Logger.getLogger(DataBase.class);

    //подключение базы данных
    Connection con;

    //параметры из файла
    Properties properties;

    DataBase(Properties p) {
        this.properties = p;
        this.makeConnection();
    }

    /**
     * Подключение к базе данных по параметрам из файла.
     */
    private void makeConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = properties.getProperty("url");
            this.con = DriverManager.getConnection(url, properties);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     * Метод добавления вакансии в базу. Либо обновление даты вакансии, если вакансия уже имеется.
     * Проверка наличия по ссылке на вакансию.
     * @param text текст вакансии
     * @param href ссылка вакансии
     * @param date дата размещения вакансии
     */
    public void addVacancy(String text, String href, String date) {

        this.createTable();

        //При наличии дубликатов по полю href обновляем дату
        String sql = "UPDATE vacancies SET date = ? WHERE href = ?;"
                + "INSERT INTO vacancies (text, href, date)"
                + "       SELECT ?, ?, ?"
                + "       WHERE NOT EXISTS (SELECT 1 FROM vacancies WHERE href = ?);";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, date);
            pst.setString(2, href);
            pst.setString(3, text);
            pst.setString(4, href);
            pst.setString(5, date);
            pst.setString(6, href);
            pst.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     * Создание таблицы на случай ее отсутствия.
     */
    private void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS vacancies ("
                + "id SERIAL PRIMARY KEY,"
                + "text varchar(255),"
                + "href varchar(255),"
                + "date varchar(20)"
                + ")";

        try (Statement st = con.createStatement()) {
            st.executeUpdate(sql);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
