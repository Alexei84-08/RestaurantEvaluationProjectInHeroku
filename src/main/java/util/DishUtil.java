package util;

import model.Dish;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DishUtil {
    public static List<Dish> getDishesByDate(List<Dish> dishes, LocalDate date) {
        return dishes.stream().filter(dish -> dish.getDate().equals(date)).collect(Collectors.toList());
    }
}
