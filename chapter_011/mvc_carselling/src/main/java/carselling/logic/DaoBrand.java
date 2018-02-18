package carselling.logic;

import carselling.model.Brand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * DAO сущности марка автомобиля.
 */
public class DaoBrand implements IDao<Brand> {

    private SessionFactory factory;

    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
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
