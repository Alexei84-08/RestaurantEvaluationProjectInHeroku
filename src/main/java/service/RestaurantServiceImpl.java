package service;

import model.Dish;
import model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RestaurantCrud;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static util.DishUtil.getDishesByDate;
import static util.ValidationUtil.checkNotFoundWithId;
import static util.ValidationUtil.isNotNew;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {
    private final Logger LOG = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    private final RestaurantCrud restaurantCrud;

    @Autowired
    public RestaurantServiceImpl(RestaurantCrud restaurantCrud) {
        this.restaurantCrud = restaurantCrud;
    }

    @Override
    public Restaurant get(Integer restaurantId) {
        LOG.info("get " + restaurantId);
        return checkNotFoundWithId(restaurantCrud.get(restaurantId), restaurantId);
    }

    @Override
    public List<Restaurant> getAll() {
        LOG.info("getAll");
        return restaurantCrud.findAll();
    }

    @Override
    public List<Dish> getHistoryDishFromRestaurant(int restaurantId) {
        LOG.info("getHistoryDishFromRestaurant " + restaurantId);
        return checkNotFoundWithId(restaurantCrud.get(restaurantId), restaurantId).getMenuHistory();
    }

    @Override
    public List<Restaurant> getByDateWithMenuHistory(LocalDate date) {
        LOG.info("getByDateWithMenuHistory");
        List<Restaurant> restaurants = restaurantCrud.getByDateWithMenuHistory();
        List<Restaurant> restaurants1 = new ArrayList<>();
        for (Restaurant r : restaurants) {
            r.setMenuHistory(getDishesByDate(r.getMenuHistory(), date));
            if (r.getMenuHistory().size() != 0) {
                restaurants1.add(r);
            }
        }
        return restaurants1;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        LOG.info("save " + restaurant);
        return restaurantCrud.save(isNotNew(restaurant));
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        LOG.info("update " + restaurant);
        checkNotFoundWithId(restaurantCrud.get(restaurant.getId()), restaurant.getId());
        return restaurantCrud.save(restaurant);
    }

    @Override
    public void delete(int restaurantId) {
        LOG.info("delete " + restaurantId);
        checkNotFoundWithId(get(restaurantId), restaurantId);
        restaurantCrud.deleteById(restaurantId);
    }
}
