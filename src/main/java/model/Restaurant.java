package model;

import com.fasterxml.jackson.annotation.*;
import model.parents.AbstractNamedEntity;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraph(name = Restaurant.GRAPH_WITH_MENU_HISTORY, includeAllAttributes = true)

@NamedQueries({
        @NamedQuery(name = Restaurant.GET_BY_ID, query = "SELECT r FROM Restaurant r WHERE r.id=?1"),
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT DISTINCT r FROM Restaurant r ORDER BY r.name")
})

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    public static final String GRAPH_WITH_MENU_HISTORY = "Restaurant.withMenuHistory";
    public static final String GET_BY_ID = "Restaurant.getById";
    public static final String GET_ALL = "Restaurant.getAll";

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL)
    @OrderBy(value = "date DESC")
    private List<Dish> menuHistory;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, List<Dish> menuHistory) {
        super(id, name);
        this.menuHistory = menuHistory;
    }

    public List<Dish> getMenuHistory() {
        return menuHistory;
    }

    public void setMenuHistory(List<Dish> menuHistory) {
        this.menuHistory = menuHistory;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "Restaurant{" +
//                "menuHistory=" + menuHistory +
//                ", name='" + name + '\'' +
//                ", id=" + id +
//                '}';
//    }
}
