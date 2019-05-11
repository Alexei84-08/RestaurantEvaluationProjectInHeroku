package service;

import model.Dish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DishCrud;
import repository.RestaurantCrud;
import to.DishTO;

import java.time.LocalDate;

import static util.ValidationUtil.checkNotFoundWithId;
import static util.ValidationUtil.isNotNew;

@Service("dishService")
public class DishServiceImpl implements DishService {
    private final Logger LOG = LoggerFactory.getLogger(DishServiceImpl.class);

    private final DishCrud dishCrud;

    private final RestaurantCrud restaurantCrud;

    @Autowired
    public DishServiceImpl(DishCrud dishCrud, RestaurantCrud restaurantCrud) {
        this.dishCrud = dishCrud;
        this.restaurantCrud = restaurantCrud;
    }

    @Override
    public Dish get(Integer dishId) {
        LOG.info("get " + dishId);
        return checkNotFoundWithId(dishCrud.getById(dishId), dishId);
    }

    @Override
    public Dish save(DishTO dishTO) {
        LOG.info("save " + dishTO);
        return dishCrud.save(isNotNew(createDish(dishTO)));
    }

    @Override
    public void delete(int dishId) {
        LOG.info("delete " + dishId);
        checkNotFoundWithId(get(dishId), dishId);
        dishCrud.deleteById(dishId);
    }

    private Dish createDish(DishTO dishTO) {
        LOG.info("createDish " + dishTO);
        return new Dish(
                dishTO.getId(),
                dishTO.getName(),
                restaurantCrud.getOne(dishTO.getRestaurantId()),
                dishTO.getPrice(),
                LocalDate.now()
        );
    }
}
