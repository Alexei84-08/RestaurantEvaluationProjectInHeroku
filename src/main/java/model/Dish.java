package model;

import model.parents.AbstractNamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "price")
    @NotNull
    private Integer price;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    public Dish() {
    }

    public Dish(Integer id, String name, Restaurant restaurant, Integer price, LocalDate date) {
        super(id, name);
        this.restaurant = restaurant;
        this.price = price;
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

//    @Override
//    public String toString() {
//        return "Dish{" +
//                "price=" + price +
//                ", date=" + date +
//                ", name='" + name + '\'' +
//                ", id=" + id +
//                '}';
//    }

        @Override
    public String toString() {
        return "Dish{" +
                "restaurantId=" + restaurant.getId() +
                ", price=" + price +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
