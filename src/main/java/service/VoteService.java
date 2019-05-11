package service;

import model.User;
import model.Vote;
import to.VoteTO;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {
    Vote get(Integer voteId);

    Vote save(Integer restaurantId, User user);

    List<Vote> getSortedByDate(LocalDate date);

    List<VoteTO> createVoteTOList(User user);
}
