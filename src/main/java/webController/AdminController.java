package webController;

import model.Dish;
import model.Restaurant;
import model.User;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import service.DishService;
import service.RestaurantService;
import service.UserService;
import to.DishTO;
import to.UserTo;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    private final UserService userService;

    private final RestaurantService restaurantService;

    private final DishService dishService;

    @Autowired
    public AdminController(UserService userService, RestaurantService restaurantService, DishService dishService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    @GetMapping("/add")
    public void add(){

    }

    @GetMapping
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity usersAll() {
        LOG.info("getUsers");
        List<User> result = userService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@Valid @RequestBody UserTo userTo) {
        LOG.info("addUser userTo = {}", userTo);
        userService.save(userTo);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userUpdate(@Valid @RequestBody UserTo userTo) {
        LOG.info("UpdateUser userTo = {}", userTo);
        userService.update(userTo);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int userId) {
        LOG.info("deleteUser userId = {}", userId);
        userService.delete(userId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserTo(@PathVariable("id") int id) {
        LOG.info("getUserTo userId = {}", id);
        return ResponseEntity.ok(userService.getUserTo(id));
    }

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity restaurantsAll() {
        LOG.info("getRestaurants");
        List<Restaurant> result = restaurantService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRestaurants(@Valid @RequestBody Restaurant restaurant) {
        LOG.info("addRestaurant restaurant = {}", restaurant);
        restaurantService.save(restaurant);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping(value = "/restaurants")
    public ResponseEntity updateRestaurants(@NotNull @Valid @RequestBody Restaurant restaurant) {
        LOG.info("UpdateRestaurant restaurant = {}", restaurant);
        restaurantService.update(restaurant);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/restaurants/{id}")
    public ResponseEntity deleteRestaurant(@PathVariable("id") int restaurantId) {
        LOG.info("deleteRestaurant restaurantsId = {}", restaurantId);
        restaurantService.delete(restaurantId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/restaurants/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDishFromRestaurant(@PathVariable("restaurantId") int restaurantId) {
        LOG.info("getRestaurant restaurantId = {}", restaurantId);
        Restaurant result = restaurantService.get(restaurantId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/restaurants/history/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHistoryDishFromRestaurant(@PathVariable("restaurantId") int restaurantId) {
        LOG.info("getHistoryDishFromRestaurant restaurantId = {}", restaurantId);
        List<Dish> result = restaurantService.getHistoryDishFromRestaurant(restaurantId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addDish(@Valid @RequestBody DishTO dishTO) {
        LOG.info("addDish dishTO = {}", dishTO);
        dishService.save(dishTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/dishes/{dishtId}")
    public ResponseEntity deletetDish (@PathVariable("dishtId") int dishtId){
        LOG.info("deletetDish dishtId = {}", dishtId);
        dishService.delete(dishtId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
