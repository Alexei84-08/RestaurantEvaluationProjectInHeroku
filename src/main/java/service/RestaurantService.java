package service;

import model.Dish;
import model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {
    Restaurant get(Integer restaurantId);

    List<Restaurant> getAll();

    List<Restaurant> getByDateWithMenuHistory(LocalDate date);

    Restaurant save(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    void delete(int restaurantId);

    List<Dish> getHistoryDishFromRestaurant(int restaurantId);
}
