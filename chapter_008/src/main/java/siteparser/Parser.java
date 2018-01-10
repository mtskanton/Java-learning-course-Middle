package siteparser;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.TimerTask;

/**
 * Класс парсинга.
 */
public class Parser extends TimerTask {

    private static final Logger LOGGER = Logger.getLogger(Parser.class);

    //параметры из файла
    Properties properties;

    //дата, начиная с которой проверяются вакансии
    String start;

    //база данных для сохранения информации о вакансиях
    DataBase db;

    Parser(Properties p) {
        this.properties = p;
        db = new DataBase(p);
    }

    /**
     * Запуск парсинга вакансий.
     */
    public void run() {
        LOGGER.info("Парсинг вакансий запущен");

        this.start = this.isLastParsingDate();
        this.parsePages();
        this.updateProperties();

        LOGGER.info("Парсинг вакансий закончен");
    }

    /**
     * Последовательный парсинг по всем страницам.
     */
    private void parsePages() {
        int last = this.getLastPage();
        for (int i = 1; i <= last; i++) {
            if (!this.parseEachPage(i)) {
                break;
            }
        }
    }

    /**
     * Метод поиска последней страницы форума вакансий.
     * @return номер страницы
     */
    private Integer getLastPage() {
        Integer lastpage = 0;
        try {
            Document document = Jsoup.connect("http://www.sql.ru/forum/job-offers").get();
            Element table = document.select("table.sort_options").last();
            Element href = table.select("a").last();
            lastpage = Integer.valueOf(href.text());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return lastpage;
    }


    /**
     * На каждой странице пробегаем по списку вакансий.
     * пока не найдем ту, у которой дата размещения старше последнего запуска
     * @param number номер страницы
     * @return false когда нашли вакансию с датой старше даты сравнения
     */
    private boolean parseEachPage(int number) {
        Document document = null;
        try {
            document = Jsoup.connect("http://www.sql.ru/forum/job-offers/" + number).get();
            Element table = document.selectFirst("table.forumTable");
            Elements els = table.select("tr");

            //проверяем дату каждой вакансии, сравнивая с последней датой запуска
            //i = 4, так как первые строки (с 0 по 3) - шапка таблицы
            for (int i = 4; i < els.size(); i++) {

                //дата вакансии - проверяем на сравнение с последним запуском программы
                String date = this.normalizeDate(els.get(i).select("td").last().text());
                if (this.isNewDate(date)) {

                    //заголовок вакансии - сопоставляем с актуальностью по теме java
                    String text = els.get(i).select("td.postslisttopic").text();
                    if (this.isRelevantText(text)) {

                        //ссылка на вакансию - для добавления в базу данных
                        String href = els.get(i).select("a").first().attr("href");
                        //добавляем в базу данных
                        this.db.addVacancy(text, href, date);
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Метод приведения даты к стандартному виду, если встречаются слова "сегодня" или "вчера"
     * @param date дата полученная из вакансии
     * @return нормализованный формат
     */
    private String normalizeDate(String date) {

        //если дата содержит слово "сегодня", сперва заменяем его соответствующей датой
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM YY", new Locale("ru"));
        if (date.contains("сегодня")) {
            String today = sdf.format(new Date());
            String time = date.substring(7, date.length());
            date = today + time;
        }

        //если дата содержит слово "вчера", сперва заменяем его соответствующей датой
        if (date.contains("вчера")) {
            Date today = new Date();
            Long timing = today.getTime() - (24 * 60 * 60 * 1000);
            Date dayBefore = new Date(timing);
            String yesterday = sdf.format(dayBefore);
            String time = date.substring(5, date.length());
            date = yesterday + time;
        }
        return date;
    }


    /**
     * Проверка даты вакансии на актуальность.
     * @param date дата вакансии
     * @return true если вакансия новая, false если была размещена до последнего запуска
     */
    private boolean isNewDate(String date) {
        boolean isNewVacancy = true;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yy, HH:mm", new Locale("ru"));

            Date vacancyDate = format.parse(date);
            Date startDate = format.parse(this.start);

            if (startDate.after(vacancyDate)) {
                isNewVacancy = false;
            }

        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return isNewVacancy;
    }

    /**
     * Получение даты последнего запуска.
     * @return дату последнего запуска из параметров или 1 января текущего года
     */
    private String isLastParsingDate() {
        String date = properties.getProperty("lastDayLaunched");
        if (date.equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yy");
            date = String.format("01 янв %s, 00:00", sdf.format(new Date()));
            System.out.println("startDate = " + date);
        }
        return date;
    }

    //

    /**
     * Проверка на релевантность вакансии.
     * JavaScript и Java Script не подходит. Заменяем их последовательно при нахождении в строке.
     * Финальную строку проверяем на наличие java
     * @param text
     * @return
     */
    private boolean isRelevantText(String text) {
        boolean relevant = false;

        String low = text.toLowerCase();
        String low1 = low.replace("javascript", "0");
        String low2 = low1.replace("java script", "0");

        if (low2.contains("java")) {
            relevant = true;
        }
        return relevant;
    }

    /**
     * Обновление даты последнего запуска в в файле с параметрами.
     */
    private void updateProperties() {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("chapter_008\\src\\main\\resources\\parsing.properties"))) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy, HH:mm", new Locale("ru"));
            String today = sdf.format(new Date());

            properties.setProperty("lastDayLaunched", today);
            properties.store(writer, null);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
