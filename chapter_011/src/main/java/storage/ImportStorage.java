package storage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import storage.logic.JdbcStorage;
import storage.model.User;


/**
 * Класс запуска приложения.
 * 1. Реализовать хранилище MemoryStorage.
 * 2. Реализовать хранилище JdbcStorage. В хранилище нужно передавать настройки.
 * 3. Сделать класс ImportUser для запуска приложения. Приложение должно позволять добавляй пользователей в базу данных.
 * 4. Сделать тесты, в которых использовать в качестве хранилища MemoryStorage.
 */
public class ImportStorage {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("storage.xml");
        JdbcStorage jdbcStorage = (JdbcStorage) context.getBean("jdbcStorage");

        User user = new User();
        user.setName("testName");
        user.setEmail("email@email.ru");
        jdbcStorage.create(user);
        System.out.println(jdbcStorage.getById(4));
    }
}
