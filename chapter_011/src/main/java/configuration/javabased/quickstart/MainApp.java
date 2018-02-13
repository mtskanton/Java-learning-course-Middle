package configuration.javabased.quickstart;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Класс приложения.
 */
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(WelcomeConfig.class);
        Welcome welcome = context.getBean(Welcome.class);
        welcome.greet();
    }
}
