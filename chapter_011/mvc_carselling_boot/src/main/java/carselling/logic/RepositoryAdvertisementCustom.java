package carselling.logic;

import carselling.model.Advertisement;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class RepositoryAdvertisementCustom {

    @PersistenceContext
    EntityManager em;

    /**
     * Получение списка объявлений с учетом фильтрации по входящим параметрам.
     * в отборе участвуют только те фильтры, значения которых определены
     * Для указания критериев используется JPA Criteria API
     * @param today фильтр "за сегодня" - отбирает даты с начала сегодняшнего дня
     * @param brand фильтр по марке авто. Есть проверка на то, что передано числовое значение
     * @return список объявлений после применения фильтров
     */
    public List<Advertisement> getAllFiltered(String today, String brand) {
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
            return em.createQuery(criteria).getResultList();
    }
}
