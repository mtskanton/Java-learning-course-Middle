package carselling.logic;

import carselling.models.Carcase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * DAO сущности кузов автомобиля.
 */
public class DaoCarcase implements IDao<Carcase> {

    private static final DaoCarcase INSTANCE = new DaoCarcase();
    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    private DaoCarcase() {
    }

    public static DaoCarcase getInstance() {
        return INSTANCE;
    }

    @Override
    public Carcase getById(int id) {
        try (Session session = factory.openSession()) {
            Carcase carcase = session.get(Carcase.class, id);
            return carcase;
        }
    }

    @Override
    public List<Carcase> getAll() {
        try (Session session = factory.openSession();) {
            Query query = session.createQuery("from Carcase");
            return query.list();
        }
    }

    @Override
    public void create(Carcase entity) {

    }

    @Override
    public void update(Carcase entity) {

    }

    @Override
    public void delete(Carcase entity) {

    }
}
