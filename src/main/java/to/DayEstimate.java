package to;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.Restaurant;
import util.Json.JsonSerializerMap;

import java.time.LocalDate;
import java.util.Map;

public class DayEstimate extends JsonSerializerMap {
    private LocalDate date;

    @JsonSerialize(keyUsing = DayEstimate.class)
    private Map<Restaurant, Integer> dayVoteResult;

    public DayEstimate() {
    }

    public DayEstimate(LocalDate date, Map<Restaurant, Integer> dayVoteResult) {
        this.date = date;
        this.dayVoteResult = dayVoteResult;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<Restaurant, Integer> getDayVoteResult() {
        return dayVoteResult;
    }

    public void setDayVoteResult(Map<Restaurant, Integer> dayVoteResult) {
        this.dayVoteResult = dayVoteResult;
    }
}