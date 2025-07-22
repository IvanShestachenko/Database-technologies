package entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"Visitor\"")
@PrimaryKeyJoinColumn(name="user_id")
public class Visitor extends User {

    @Column(name = "notifications_on_new_events", nullable = false)
    private Boolean notificationsOnNewEvents = false;

    @ManyToMany
    @JoinTable(name = "\"Set_favorite\"",
            joinColumns = @JoinColumn(name = "visitor_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "\"Visiting\"",
            joinColumns = @JoinColumn(name = "visitor_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new LinkedHashSet<>();

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Boolean getNotificationsOnNewEvents() {
        return notificationsOnNewEvents;
    }

    public void setNotificationsOnNewEvents(Boolean notificationsOnNewEvents) {
        this.notificationsOnNewEvents = notificationsOnNewEvents;
    }

}