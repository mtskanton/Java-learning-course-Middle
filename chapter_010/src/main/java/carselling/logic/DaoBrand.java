package carselling.logic;

import carselling.models.Brand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;

/**
 * DAO сущности марка автомобиля.
 */
public class DaoBrand implements IDao<Brand> {

    private static final DaoBrand INSTANCE = new DaoBrand();
    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    private DaoBrand() {
    }

    public static DaoBrand getInstance() {
        return INSTANCE;
    }

    @Override
    public Brand getById(int id) {
        try (Session session = factory.openSession()) {
            Brand brand = session.get(Brand.class, id);
            return brand;
        }
    }

    @Override
    public List<Brand> getAll() {
        try (Session session = factory.openSession();) {
            Query query = session.createQuery("from Brand");
            return query.list();
        }
    }

    @Override
    public void create(Brand brand) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(brand);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Brand entity) {

    }

    @Override
    public void delete(Brand entity) {

    }
}
