package DAO;

import entities.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public class EventDAO {
    private final EntityManager em;

    public EventDAO(EntityManager em) {
        this.em = em;
    }

    public List<Event> getAllEvents() {
        return em.createQuery("SELECT e FROM Event e", Event.class)
                .getResultList();
    }

    public Event getEventById(Integer id) {
        return em.find(Event.class, id);
    }

    public Event getEarliestEvent() {
        return em.createQuery("SELECT e FROM Event e ORDER BY e.startingAt LIMIT 1", Event.class)
                .getSingleResult();
    }

    public List<Event> getEventsInCity(String city) {
        return em.createQuery(
                        "SELECT e FROM Event e WHERE e.city = :city", Event.class)
                .setParameter("city", city)
                .getResultList();
    }

    public List<Event> getEventsWithAtLeastNVisitors(int n) {
        return em.createQuery(
                        "SELECT e FROM Event e WHERE (SELECT COUNT(v) FROM e.visitors v) >= :n", Event.class)
                .setParameter("n", n)
                .getResultList();
    }

    public void addEvent(Event event) {
        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();
    }

    public void removeEvent(Event event) {
        em.getTransaction().begin();
        em.remove(em.contains(event) ? event : em.merge(event));
        em.getTransaction().commit();
    }
}
