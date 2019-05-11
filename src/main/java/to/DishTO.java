package to;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DishTO {
    @Id
    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer price;

    @Id
    @NotNull
    private Integer restaurantId;

    public DishTO() {
    }

    public DishTO(Integer id, @NotEmpty String name, @NotNull Integer price, @NotNull Integer restaurantId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
