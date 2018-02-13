package configuration.xml.constructor;

import java.text.SimpleDateFormat;

/**
 * Класс корректировки отображения даты.
 */
public class DateEditor {

    private DateProvider printer;

    DateEditor(DateProvider printer) {
        this.printer = printer;
    }

    public void getDateInfo() {
        System.out.println("Текущая дата: ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(printer.getDate());
        System.out.println(date);
    }

    public void init() {
        System.out.println("init DateEditor xml method");
    }

    public void destroy() {
        System.out.println("destroy DateEditor xml method");
    }
}
