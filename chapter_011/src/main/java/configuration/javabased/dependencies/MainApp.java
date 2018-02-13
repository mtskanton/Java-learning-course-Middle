package configuration.javabased.dependencies;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Класс приложения.
 */
public class MainApp {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(GetDateConfig.class);
        DateEditor editor = context.getBean(DateEditor.class);
        editor.getDateInfo();
        context.registerShutdownHook();
    }
}
