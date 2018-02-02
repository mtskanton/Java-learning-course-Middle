package carselling.logic;

import carselling.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * DAO сущности пользователь.
 */
public class DaoUser implements IDao<User> {

    private static final DaoUser INSTANCE = new DaoUser();
    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    private DaoUser() {
    }

    public static DaoUser getInstance() {
        return INSTANCE;
    }

    @Override
    public User getById(int id) {
        try (Session session = factory.openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    /**
     * Метод проверки регистрации пользователя.
     * @param login логин
     * @param password пароль
     * @return пользователя с этими учетными данными
     */
    public User isRegistered(String login, String password) {
        User user = null;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("from User where login=:login and password=:password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            List<User> users = query.list();
            if (users.size() != 0) {
                user = users.get(0);
            }
        }
        return user;
    }
}
