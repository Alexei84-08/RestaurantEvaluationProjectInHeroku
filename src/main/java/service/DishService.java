package service;

import model.Dish;
import to.DishTO;

public interface DishService {
    Dish get(Integer dishId);

    Dish save(DishTO dishTO);

    void delete(int dishId);
}
