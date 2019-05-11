package repository;

import model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantCrud extends JpaRepository<Restaurant, Integer> {
    @EntityGraph(Restaurant.GRAPH_WITH_MENU_HISTORY)
    @Query(name = Restaurant.GET_BY_ID)
    Restaurant get(Integer id);

    @EntityGraph(Restaurant.GRAPH_WITH_MENU_HISTORY)
    @Query(name = Restaurant.GET_ALL)
    List<Restaurant> getByDateWithMenuHistory();
}
