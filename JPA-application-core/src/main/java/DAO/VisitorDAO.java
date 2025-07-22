package DAO;

import entities.Visitor;
import jakarta.persistence.EntityManager;
import java.util.List;

public class VisitorDAO {
    private final EntityManager em;

    public VisitorDAO(EntityManager em) {
        this.em = em;
    }

    public List<Visitor> getAllVisitors() {
        return em.createQuery("SELECT v FROM Visitor v", Visitor.class)
                .getResultList();
    }

    public List<Visitor> getVisitorsWithAtLeastNEvents(int n) {
        return em.createQuery(
                        "SELECT v FROM Visitor v " +
                                "WHERE (SELECT COUNT(e) FROM v.events e) >= :n",
                        Visitor.class
                )
                .setParameter("n", n)
                .getResultList();
    }
}
