package entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"Organizer\"")
@PrimaryKeyJoinColumn(name="user_id")
public class Organizer extends User {

    @Column(name = "\"IČO\"", nullable = false)
    private String ičo;

    @Column(name = "typ_osoby", nullable = false)
    private String typOsoby;

    @ManyToMany
    @JoinTable(name = "\"Organizing\"",
            joinColumns = @JoinColumn(name = "organizer_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events = new LinkedHashSet<>();

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public String getIčo() {
        return ičo;
    }

    public void setIčo(String ičo) {
        this.ičo = ičo;
    }

    public String getTypOsoby() {
        return typOsoby;
    }

    public void setTypOsoby(String typOsoby) {
        this.typOsoby = typOsoby;
    }

}