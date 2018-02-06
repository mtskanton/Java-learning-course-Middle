package todolist.logic;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Фабрика сессии.
 */
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .configure()
            .buildSessionFactory();

    private HibernateUtil() {
    }

    public static SessionFactory getFactory() {
        return SESSION_FACTORY;
    }
}
