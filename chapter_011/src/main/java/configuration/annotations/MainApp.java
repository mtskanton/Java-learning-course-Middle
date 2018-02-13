package configuration.annotations;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Основное приложение.
 */
public class MainApp {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserInfoPrinter printer = (UserInfoPrinter) context.getBean("userInfoPrinter");
        printer.getName();
        printer.getAge();
        context.registerShutdownHook();
    }
}
