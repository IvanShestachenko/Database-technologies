package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EventPartId implements Serializable {
    private static final long serialVersionUID = -6397066491531919249L;
    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "order_number", nullable = false)
    private Long orderNumber;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventPartId entity = (EventPartId) o;
        return Objects.equals(this.eventId, entity.eventId) &&
                Objects.equals(this.orderNumber, entity.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, orderNumber);
    }

}