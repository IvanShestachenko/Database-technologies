package service;

import DAO.VisitorDAO;
import entities.Visitor;

import java.util.List;

public class VisitorService {
    private final VisitorDAO visitorDAO;

    public VisitorService(VisitorDAO visitorDAO) {
        this.visitorDAO = visitorDAO;
    }

    public void printVisitorInformation(Visitor visitor) {
        System.out.println("Visitor ID: " + visitor.getId());
        System.out.println("Login: " + visitor.getLogin());
        System.out.println("Email: " + visitor.getEmail());
        System.out.println("Events attended: " + visitor.getEvents().size());
    }

    public void getVisitorsWithAtLeastNEvents(int n) {
        List<Visitor> visitors = visitorDAO.getVisitorsWithAtLeastNEvents(n);
        System.out.println("Visitors with at least " + n + " events:\n");
        for (Visitor visitor : visitors) {
            printVisitorInformation(visitor);
            System.out.println("---");
        }
    }
}