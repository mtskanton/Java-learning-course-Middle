package carselling.logic;

import carselling.models.Advertisement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * DAO сущности Объявление.
 */
public class DaoAdvertisement implements IDao<Advertisement> {

    private static final DaoAdvertisement INSTANCE = new DaoAdvertisement();
    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    private DaoAdvertisement() {
    }

    public static DaoAdvertisement getInstance() {
        return INSTANCE;
    }

    @Override
    public Advertisement getById(int id) {
        try (Session session = factory.openSession()) {
            Advertisement ad = session.get(Advertisement.class, id);
            return ad;
        }
    }

    @Override
    public List<Advertisement> getAll() {
        try (Session session = factory.openSession();) {
            Query query = session.createQuery("from Advertisement");
            return query.list();
        }
    }

    @Override
    public void create(Advertisement ad) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(ad);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Advertisement ad) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(ad);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Advertisement ad) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(ad);
            session.getTransaction().commit();
        }
    }
}
