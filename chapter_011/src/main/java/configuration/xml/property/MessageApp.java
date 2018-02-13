package configuration.xml.property;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Класс приложения.
 */
public class MessageApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MessagePrinter printer = (MessagePrinter) context.getBean("helloWorld");
        printer.getMessage();
    }
}
