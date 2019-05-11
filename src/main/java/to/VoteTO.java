package to;

import model.Vote;

import java.time.LocalDateTime;
import java.util.Objects;

public class VoteTO {
    private LocalDateTime dateTime;

    private Integer restaurantId;

    private String restaurantName;

    public VoteTO(LocalDateTime dateTime, Integer restaurantId, String restaurantName) {
        this.dateTime = dateTime;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
    }

    public VoteTO(Vote vote) {
        this.dateTime = LocalDateTime.of(vote.getDate(), vote.getTime());
        this.restaurantId = vote.getRestaurant().getId();
        this.restaurantName = vote.getRestaurant().getName();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteTO voteTO = (VoteTO) o;
        return Objects.equals(dateTime, voteTO.dateTime) &&
                Objects.equals(restaurantId, voteTO.restaurantId) &&
                Objects.equals(restaurantName, voteTO.restaurantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, restaurantId, restaurantName);
    }

    @Override
    public String toString() {
        return "VoteTO{" +
                "dateTime=" + dateTime +
                ", restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }
}
