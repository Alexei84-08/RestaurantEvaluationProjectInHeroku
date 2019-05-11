package service;

import model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.exception.IllegalRequestDataException;
import util.exception.NotFoundException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static testdata.DishTestData.*;
import static testdata.RestaurantTestData.*;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService restaurantService;

    @Test
    void get() {
        assertThat(restaurantService.get(STARDOGS.getId())).isEqualToIgnoringGivenFields(STARDOGS, "menuHistory");
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantService.get(NO_RESTAURANT));
    }

    @Test
    void getAll() {
        assertMatch(restaurantService.getAll(), RESTAURANTS_TODAY);
    }

    @Test
    void getHistoryDishFromRestaurant() {
        assertThat(restaurantService.getHistoryDishFromRestaurant(VILKA_LOSHKA.getId())).containsExactlyInAnyOrder(DISH114, DISH115, DISH116, DISH117);
    }

    @Test
    void getHistoryDishFromRestaurantNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantService.getHistoryDishFromRestaurant(NO_RESTAURANT));
    }

    @Test
    void getByDateWithMenuHistory() {
        assertMatch(restaurantService.getByDateWithMenuHistory(LocalDate.now()), RESTAURANTS_TODAY);
    }

    @Test
    void save() {
        Restaurant created = restaurantService.save(NEW_RESTAURANT);
        Restaurant actual = restaurantService.get(created.getId());
        assertThat(actual).isEqualToIgnoringGivenFields(created, "menuHistory");
    }

    @Test
    void saveIllegalArgumentException() {
        assertThrows(IllegalRequestDataException.class, () -> restaurantService.save(NEW_RESTAURANT_EX));
    }

    @Test
    void update() {
        Restaurant expected = restaurantService.update(MUMU_UPDATE);
        assertThat(restaurantService.get(MUMU.getId())).isEqualToIgnoringGivenFields(expected, "menuHistory");
    }

    @Test
    void updateNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantService.update(NEW_RESTAURANT_EX));
    }

    @Test
    void delete() {
        restaurantService.delete(JAR_PIZZA.getId());
        assertMatch(restaurantService.getAll(), RESTAURANTS_WITHOUT_JAR_PIZZA);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantService.delete(NO_RESTAURANT));
    }
}