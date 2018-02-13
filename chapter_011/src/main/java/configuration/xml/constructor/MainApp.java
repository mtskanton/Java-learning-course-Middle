package configuration.xml.constructor;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Класс приложения.
 */
public class MainApp {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        DateEditor editor = (DateEditor) context.getBean("dateEditor");
        editor.getDateInfo();
        context.registerShutdownHook();
    }
}
