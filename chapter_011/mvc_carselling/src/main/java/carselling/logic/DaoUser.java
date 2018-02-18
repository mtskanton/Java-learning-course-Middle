package carselling.logic;

import carselling.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * DAO сущности пользователь.
 */
public class DaoUser implements IDao<User> {

    private SessionFactory factory;

    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
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
    public void create(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
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
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("from User where login=:login and password=:password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            return (User) query.uniqueResult();
        }
    }
}
