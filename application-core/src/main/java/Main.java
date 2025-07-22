import entities.Event;
import entities.Visitor;
import service.EventService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import DAO.*;
import service.VisitorService;
import java.util.List;
import java.time.OffsetDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        
        EventDAO eventDAO = new EventDAO(em);
        EventService eventService = new EventService(eventDAO);
        
        VisitorDAO visitorDAO = new VisitorDAO(em);
        VisitorService visitorService = new VisitorService(visitorDAO);

        System.out.println("=== EVENT SERVICE DEMONSTRATION ===\n");

        System.out.println("1. Finding the earliest event:");
        eventService.showEarliestEvent();

        System.out.println("2. Finding events in Praha:");
        eventService.showEventsInTheCity("Praha");

        System.out.println("3. Finding popular events (at least 2000 visitors):");
        eventService.showEventsWIthAtLeastNVisitors(2000);

        System.out.println("4. Creating a new event:");
        OffsetDateTime event1Start = OffsetDateTime.now().plusDays(30);
        Event createdEvent1 = eventService.createEvent(
                "Crazy Charles Bridge Party",
                event1Start,
                "Praha",
                "Karluv most",
                1L,
                event1Start.plusHours(4),
                1000L,
                500L,
                "Come celebrate with us on the famous bridge!");

        System.out.println("5. Creating another event:");
        OffsetDateTime event2Start = OffsetDateTime.now().plusDays(45);
        Event createdEvent2 = eventService.createEvent(
                "Summer Music Festival",
                event2Start,
                "Brno",
                "Náměstí Svobody",
                15L,
                event2Start.plusHours(6),
                2500L,
                750L,
                "Annual summer music celebration in the heart of Brno");

        System.out.println("6. Finding events in Brno (after creation):");
        eventService.showEventsInTheCity("Brno");

        System.out.println("7. Final state - all events in database:");
        List<Event> finalEvents = eventDAO.getAllEvents();
        System.out.println("Total events in database: " + finalEvents.size());
        for (Event event : finalEvents) {
            eventService.printEventInformation(event);
            System.out.println("---");
        }

        System.out.println("8. Removing the created events by ID:");
        if (createdEvent1 != null && createdEvent1.getId() != null) {
            System.out.println("Removing event: " + createdEvent1.getName() + " (ID: " + createdEvent1.getId() + ")");
            eventService.removeEventById(createdEvent1.getId());
            Event removedEvent1 = eventDAO.getEventById(createdEvent1.getId());
            if (removedEvent1 == null) {
                System.out.println("Confirmation: Event successfully removed from database");
            }
        }
        if (createdEvent2 != null && createdEvent2.getId() != null) {
            System.out.println("Removing event: " + createdEvent2.getName() + " (ID: " + createdEvent2.getId() + ")");
            eventService.removeEventById(createdEvent2.getId());
            Event removedEvent2 = eventDAO.getEventById(createdEvent2.getId());
            if (removedEvent2 == null) {
                System.out.println("Confirmation: Event successfully removed from database");
            }
        }

        System.out.println("9. Finding events with moderate attendance (at least 1000 visitors):");
        eventService.showEventsWIthAtLeastNVisitors(1000);

        System.out.println("\n=== VISITOR SERVICE DEMONSTRATION ===\n");

        System.out.println("10. Finding active visitors (at least 5 events):");
        visitorService.getVisitorsWithAtLeastNEvents(5);

        System.out.println("11. Finding moderately active visitors (at least 2 events):");
        visitorService.getVisitorsWithAtLeastNEvents(2);

        System.out.println("12. Displaying all visitors:");
        List<Visitor> allVisitors = visitorDAO.getAllVisitors();
        System.out.println("Total visitors in database: " + allVisitors.size());
        for (Visitor visitor : allVisitors) {
            visitorService.printVisitorInformation(visitor);
            System.out.println("---");
        }

        System.out.println(
            """
            DEMONSTRATION COMPLETE
            This demonstration shows:
                - Event creation (database insertion)
                - Event deletion (database removal)
                - Complex queries with criteria
                - Service layer business logic
                - DAO layer data access patterns
                - Entity relationships and navigation
            """
        );

        em.close();
        emf.close();
    }
}