package service;

import model.Restaurant;
import model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.DayEstimate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

@Service("dayEstimateCreator")
public class DayEstimateCreator {

    private final VoteService voteService;

    @Autowired
    public DayEstimateCreator(VoteService voteService) {
        this.voteService = voteService;
    }

    public DayEstimate createDayEstimate(LocalDate date) {
        Map<Restaurant, Integer> result = new HashMap<>();
        List<Vote> todayVotes = voteService.getSortedByDate(date);
        List<Integer> votedUsers = new ArrayList<>();
        fillResult(result, todayVotes, votedUsers);
        return new DayEstimate(date, sortByValue(result));
    }

    private void fillResult(Map<Restaurant, Integer> dayVoteResult, List<Vote> votes, List<Integer> votedUsers) {
        votes.stream().filter(vote -> !votedUsers.contains(vote.getUser().getId()))
                .forEachOrdered(vote -> {
                    Restaurant restaurant = vote.getRestaurant();
                    if (dayVoteResult.keySet().contains(restaurant)) {
                        dayVoteResult.put(restaurant, dayVoteResult.get(restaurant) + 1);
                    } else {
                        dayVoteResult.put(restaurant, 1);
                    }
                    votedUsers.add(vote.getUser().getId());
                });
    }

    private static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue(), Comparator.reverseOrder()))
                .forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }
}
