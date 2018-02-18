package carselling.logic;

import carselling.model.Carcase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

/**
 * DAO сущности кузов автомобиля.
 */
public class DaoCarcase implements IDao<Carcase> {

    private SessionFactory factory;

    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
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
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("from Carcase");
            return query.list();
        }
    }

    @Override
    public void create(Carcase carcase) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(carcase);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Carcase entity) {

    }

    @Override
    public void delete(Carcase entity) {

    }
}
