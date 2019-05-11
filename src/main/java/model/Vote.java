package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.parents.AbstractBaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Vote.GET_SORTED_BY_DATE, query = "SELECT DISTINCT v FROM Vote v WHERE v.date=?1 ORDER BY v.time DESC")
})
@NamedEntityGraph(name = Vote.GRAPH_WITH_USER_AND_RESTAURANT, includeAllAttributes = true)

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    public static final String GRAPH_WITH_USER_AND_RESTAURANT = "Vote.withUserAndRestaurant";

    public static final String GET_SORTED_BY_DATE = "Vote.getSortedByDate";

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate date;

    @Column(name = "time", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime time;

    public Vote() {
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDateTime dateTime) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
    }

    public Vote(User user, Restaurant restaurant, LocalDateTime dateTime) {
        this.user = user;
        this.restaurant = restaurant;
        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

//    @Override
//    public String toString() {
//        return "Vote{" +
//                "date=" + date +
//                ", time=" + time +
//                ", id=" + id +
//                '}';
//    }

        @Override
    public String toString() {
        return "Vote{" +
                "userId=" + user.getId() +
                ", restaurantId=" + restaurant.getId() +
                ", date=" + date +
                ", time=" + time +
                ", id=" + id +
                '}';
    }
}
