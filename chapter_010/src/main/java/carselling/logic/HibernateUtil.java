package carselling.logic;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Получение фабрики сессии.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
