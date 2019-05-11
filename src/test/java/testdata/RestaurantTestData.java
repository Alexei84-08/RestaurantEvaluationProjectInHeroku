package testdata;

import model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static Restaurant STARDOGS = new Restaurant(105, "Стардог!s", null);
    public static Restaurant VILKA_LOSHKA = new Restaurant(106, "Вилка-ложка", null);
    public static Restaurant JAR_PIZZA = new Restaurant(107, "Жар-пицца", null);
    public static Restaurant KROSHKA_KARTOSHKA = new Restaurant(108, "Крошка картошка", null);
    public static Restaurant MUMU = new Restaurant(109, "Му-му", null);

    public static List<Restaurant> RESTAURANTS_TODAY = Arrays.asList(STARDOGS, VILKA_LOSHKA, JAR_PIZZA, KROSHKA_KARTOSHKA, MUMU);
    public static List<Restaurant> RESTAURANTS_WITHOUT_JAR_PIZZA = Arrays.asList(STARDOGS, VILKA_LOSHKA, KROSHKA_KARTOSHKA, MUMU);

    public static Restaurant NEW_RESTAURANT = new Restaurant(null, "Рига", null);
    public static Restaurant NEW_RESTAURANT_EX = new Restaurant(5000, "Рига", null);
    public static Restaurant MUMU_UPDATE = new Restaurant(109, "Му-му-му", null);

    public static int NO_RESTAURANT = 15;

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).containsOnlyElementsOf(expected);
    }
}
