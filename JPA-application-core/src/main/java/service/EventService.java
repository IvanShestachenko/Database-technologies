package service;

import DAO.EventDAO;
import entities.Event;

import java.time.OffsetDateTime;
import java.util.List;

public class EventService {
    private final EventDAO eventDAO;

    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public void printEventInformation(Event event) {
        System.out.println("Event ID: " + event.getId());
        System.out.println("Name: " + event.getName());
        System.out.println("Description: " + event.getDescription());
        System.out.println("City: " + event.getCity());
        System.out.println("Starting at: " + event.getStartingAt());
        System.out.println("Entrance fee: " + event.getEntranceFee());

    }

    public void showEarliestEvent() {
        Event event = eventDAO.getEarliestEvent();
        System.out.println("Earliest event:\n");
        printEventInformation(event);
        System.out.println("---");
    }

    public void showEventsInTheCity(String city) {
        List<Event> events = eventDAO.getEventsInCity(city);
        System.out.println("Events in " + city + ":\n");
        for (Event event : events) {
            System.out.println("Event name: " + event.getName());
            System.out.println("City: " + event.getCity());
            System.out.println("Starting at: " + event.getStartingAt());
            System.out.println("---");
        }
    }

    public void showEventsWIthAtLeastNVisitors(int n) {
        List<Event> events = eventDAO.getEventsWithAtLeastNVisitors(n);
        System.out.println("Events with at least " + n + " visitors:\n");
        for (Event event : events) {
            System.out.println("Event ID: " + event.getId());
            System.out.println("Name: " + event.getName());
            System.out.println("Visitor count: " + event.getVisitors().size());
            System.out.println("Capacity: " + event.getCapacity());
            System.out.println("---");
        }

    }

    public Event createEvent(String name, OffsetDateTime startingAt, String city, String street, Long houseNumber, OffsetDateTime endsAt, Long capacity, Long entranceFee, String description) {
        Event event = new Event();
        event.setName(name);
        event.setStartingAt(startingAt);
        event.setCity(city);
        event.setStreet(street);
        event.setHouseNumber(houseNumber);
        event.setEndsAt(endsAt);
        event.setCapacity(capacity);
        event.setEntranceFee(entranceFee);
        event.setDescription(description);

        try {
            eventDAO.addEvent(event);
            System.out.println("Event created: " + name);
            return event;
        } catch (Exception e) {
            System.out.println("Failed to create event: " + e.getMessage());
            return null;
        }
    }

    public void removeEventById(Integer id) {
        Event event = eventDAO.getEventById(id);
        if (event != null) {
            eventDAO.removeEvent(event);
            System.out.println("Event with ID " + id + " has been removed.");
        } else {
            System.out.println("Event with ID " + id + " not found.");
        }
    }
}