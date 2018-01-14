package siteparser;

import org.apache.log4j.Logger;
import java.io.*;
import java.util.*;

/*
ЗАДАНИЕ:
1. Реализовать модуль сборки и анализа данных с sql.ru.
2. Система должна использовать Jsoup для скачивания страницы.
3. Система должна запускаться раз в день. Предусмотреть изменения частоты запуска.
4. Система должна собирать данные только про вакансии java. Учесть что JavaScript не подходит, как и Java Script.
5. Данные должны храниться в базе данных.
6. Учесть дубликаты.
7. Учитывать время последнего запуска. Если это первый запуск, то нужно собрать все объявления с начало года.
8. В системе не должно быть вывода либо ввода информации. Все настройки должны быть в файле.
9. Для вывода нужной информации использовать логгер log4j.
 */


/**
 * Приложение для парсинга данных с сайта sql.ru
 * Параметры запуска хранятся в файле parsing.properties
 */
public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class);

    /**
     * Метод получения установок из файла.
     */
    public Properties getProperties() {
        Properties properties = null;

        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("parsing.properties");
            Reader reader = new InputStreamReader(is)) {
            properties = new Properties();
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return properties;
    }


    public static void main(String[] args) {

        Application app = new Application();
        Properties properties = app.getProperties();

        //заданный параметр количества запусков в сутки
        int launches = Integer.valueOf(properties.getProperty("launchesPerDay"));
        int period = (24 * 60 * 60 * 1000) / launches;

        TimerTask parser = new Parser(properties);
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(parser, 0, period);
    }
}
