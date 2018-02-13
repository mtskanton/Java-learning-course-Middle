package configuration.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Класс печати информации пользователя.
 */
public class UserInfoPrinter {

    @Autowired
    @Qualifier("mainUser")
    private User user;

    public void getName() {
        System.out.println(user.getName());
    }

    public void getAge() {
        System.out.println(user.getAge());
    }

    @PostConstruct
    public void init() {
        System.out.println("init UserInfoPrinter");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy UserInfoPrinter");
    }
}
