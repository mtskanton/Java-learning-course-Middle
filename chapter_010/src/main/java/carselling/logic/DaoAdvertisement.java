package carselling.logic;

import carselling.models.Advertisement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    /**
     * Получение списка объявлений с учетом фильтрации по входящим параметрам.
     * в отборе участвуют только те фильтры, значения которых определены
     * Для указания критериев используется JPA Criteria API
     * @param today фильтр "за сегодня" - отбирает даты с начала сегодняшнего дня
     * @param brand фильтр по марке авто. Есть проверка на то, что передано числовое значение
     * @return список объявлений после применения фильтров
     */
    public List<Advertisement> getAllFiltered(String today, String brand) {
        try (Session session = factory.openSession()) {
            EntityManager em = session.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Advertisement> criteria = cb.createQuery(Advertisement.class);
            Root<Advertisement> root = criteria.from(Advertisement.class);
            criteria.select(root);
            List<Predicate> predicates = new ArrayList<>();
            if (today != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    long morning = sdf.parse(sdf.format(new Date())).getTime();
                    predicates.add(cb.greaterThanOrEqualTo(root.get("date"), new Timestamp(morning)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (brand != null) {
                try {
                    predicates.add(cb.equal(root.get("brand").get("id"), Integer.valueOf(brand)));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            }
            if (!predicates.isEmpty()) {
                criteria.where(predicates.toArray(new Predicate[0]));
            }
            em.getTransaction().commit();
            return em.createQuery(criteria).getResultList();
        }
    }
}