package todolist.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import todolist.models.Item;

import java.util.List;

/**
 * Слой взаимодействия с БД.
 */
public class ItemStorage {

    private static final ItemStorage INSTANCE = new ItemStorage();
    private final SessionFactory factory = HibernateUtil.getFactory();

    private ItemStorage() {
    }

    public static ItemStorage getInstance() {
        return INSTANCE;
    }

    /**
     * Получить все дела.
     * @return список
     */
    public List<Item> getAll() {
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("from Item order by date");
            return query.list();
        }
    }

    /**
     * Получить все невыполненные дела.
     * @return список
     */
    public List<Item> getAllUndone() {
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("from Item where done=false order by date");
            return query.list();
        }
    }

    /**
     * Добавить дело.
     * @param item дело
     * @return дело
     */
    public Item add(Item item) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item;
    }

    /**
     * Переключить статус дела выполнено/нет.
     * @param id дела
     * @param done статус выполнено
     */
    public void toggleDone(int id, boolean done) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("update Item set done=:isDone where id=:itemId");
            query.setParameter("isDone", done);
            query.setParameter("itemId", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
