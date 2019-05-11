package testdata;

import model.Dish;
import to.DishTO;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static testdata.RestaurantTestData.*;

public class DishTestData {

    public static Dish DISH110 = new Dish(110, "Французский хот-дог", STARDOGS, 100, LocalDate.now());
    public static Dish DISH111 = new Dish(111, "Датский хот-дог", STARDOGS, 120, LocalDate.now());
    public static Dish DISH112 = new Dish(112, "Бифбургер", STARDOGS, 150, LocalDate.now());
    public static Dish DISH113 = new Dish(113, "Рулле", STARDOGS, 99, LocalDate.now().minus(Period.ofDays(1)));
    public static Dish DISH114 = new Dish(114, "Салат Цезарь", VILKA_LOSHKA, 70, LocalDate.now());
    public static Dish DISH115 = new Dish(115, "Борщ", VILKA_LOSHKA, 50, LocalDate.now());
    public static Dish DISH116 = new Dish(116, "Солянка домашняя", VILKA_LOSHKA, 65, LocalDate.now());
    public static Dish DISH117 = new Dish(117, "Свинина по-московски", VILKA_LOSHKA, 120, LocalDate.now().minus(Period.ofDays(1)));
    public static Dish DISH118 = new Dish(118, "Пицца Баварская", JAR_PIZZA, 550, LocalDate.now());
    public static Dish DISH119 = new Dish(119, "Пицца Пепперони", JAR_PIZZA, 520, LocalDate.now());
    public static Dish DISH120 = new Dish(120, "Куриные ножки", JAR_PIZZA, 265, LocalDate.now().minus(Period.ofDays(1)));
    public static Dish DISH121 = new Dish(121, "Стрипсы", JAR_PIZZA, 180, LocalDate.now().minus(Period.ofDays(1)));
    public static Dish DISH122 = new Dish(122, "Сырники", KROSHKA_KARTOSHKA, 50, LocalDate.now());
    public static Dish DISH123 = new Dish(123, "Клубенёк по-французски", KROSHKA_KARTOSHKA, 70, LocalDate.now());
    public static Dish DISH124 = new Dish(124, "Краб Ролл", KROSHKA_KARTOSHKA, 90, LocalDate.now());
    public static Dish DISH125 = new Dish(125, "Потато Дог", KROSHKA_KARTOSHKA, 100, LocalDate.now().minus(Period.ofDays(1)));
    public static Dish DISH126 = new Dish(126, "Салат мясной", MUMU, 100, LocalDate.now());
    public static Dish DISH127 = new Dish(127, "Лобио", MUMU, 90, LocalDate.now());
    public static Dish DISH128 = new Dish(128, "Лапша куриная", MUMU, 110, LocalDate.now());
    public static Dish DISH129 = new Dish(129, "Харчо", MUMU, 110, LocalDate.now().minus(Period.ofDays(1)));


    public static DishTO TO_NEW_DISH = new DishTO(null, "Пирог с малиной", 100, KROSHKA_KARTOSHKA.getId());
    public static DishTO TO_NEW_DISH_EX = new DishTO(5000, "Пирог с малиной", 100, KROSHKA_KARTOSHKA.getId());

    public static List<Dish> STARDOGS_MENU_WITHOUT_DISH110 = Arrays.asList(DISH111, DISH112, DISH113);

    public static int NO_DISH = 15;

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).containsOnlyElementsOf(expected);
    }
}
