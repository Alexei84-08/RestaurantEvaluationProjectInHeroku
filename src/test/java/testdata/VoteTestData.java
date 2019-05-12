package testdata;

import model.Vote;
import to.VoteTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


import static testdata.RestaurantTestData.*;
import static testdata.UserTestData.*;

public class VoteTestData {

    public static Vote VOTE130 = new Vote(130, USER_USER, STARDOGS, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 15)));
    public static Vote VOTE131 = new Vote(131, USER_USER, KROSHKA_KARTOSHKA, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 25)));
    public static Vote VOTE132 = new Vote(132, USER_USER, MUMU, LocalDateTime.of(LocalDate.now().minus(Period.ofDays(1)), LocalTime.of(10, 15)));
    public static Vote VOTE133 = new Vote(133, USER_USER, JAR_PIZZA, LocalDateTime.of(LocalDate.now().minus(Period.ofDays(1)), LocalTime.of(10, 30)));
    public static Vote VOTE134 = new Vote(134, USER_ALEX, VILKA_LOSHKA, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 50)));
    public static Vote VOTE135 = new Vote(135, USER_ALEX, JAR_PIZZA, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 45)));
    public static Vote VOTE136 = new Vote(136, USER_ALEX, MUMU, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 20)));
    public static Vote VOTE137 = new Vote(137, USER_ALEX, KROSHKA_KARTOSHKA, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 35)));
    public static Vote VOTE138 = new Vote(138, USER_ALEX, JAR_PIZZA, LocalDateTime.of(LocalDate.now().minus(Period.ofDays(1)), LocalTime.of(7, 10)));
    public static Vote VOTE139 = new Vote(139, USER_JOHN_DOE, STARDOGS, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 5)));
    public static Vote VOTE140 = new Vote(140, USER_JOHN_DOE, KROSHKA_KARTOSHKA, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 35)));
    public static Vote VOTE141 = new Vote(141, USER_JOHN_DOE, VILKA_LOSHKA, LocalDateTime.of(LocalDate.now().minus(Period.ofDays(1)), LocalTime.of(7, 50)));
    public static Vote VOTE142 = new Vote(142, USER_JANE_DOE, JAR_PIZZA, LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 40)));
    public static Vote VOTE143 = new Vote(143, USER_JANE_DOE, KROSHKA_KARTOSHKA, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 50)));
    public static Vote VOTE144 = new Vote(144, USER_JANE_DOE, MUMU, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 55)));
    public static Vote VOTE145 = new Vote(145, USER_JANE_DOE, MUMU, LocalDateTime.of(LocalDate.now().minus(Period.ofDays(1)), LocalTime.of(7, 10)));
    public static Vote VOTE146 = new Vote(146, USER_ADMIN_N, STARDOGS, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 15)));

    public static List<Vote> VOTES_TODAY = asList(VOTE130, VOTE131, VOTE134, VOTE135, VOTE136, VOTE137, VOTE139,
            VOTE140, VOTE142, VOTE143, VOTE144, VOTE146);
    public static List<VoteTO> VOTES_TO_USER_USER = asList(new VoteTO(VOTE133), new VoteTO(VOTE132), new VoteTO(VOTE131), new VoteTO(VOTE130));

    public static int NO_VOTE = 15;

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).containsOnlyElementsOf(expected);
    }
}
