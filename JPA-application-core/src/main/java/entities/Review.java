package entities;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "\"Review\"")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Visitor user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "rating", nullable = false)
    private Short rating;

    @Column(name = "review_text")
    private String reviewText;

    @Column(name = "date_of_post", nullable = false)
    private OffsetDateTime dateOfPost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Visitor getUser() {
        return user;
    }

    public void setUser(Visitor user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public OffsetDateTime getDateOfPost() {
        return dateOfPost;
    }

    public void setDateOfPost(OffsetDateTime dateOfPost) {
        this.dateOfPost = dateOfPost;
    }

}