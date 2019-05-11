package service;

import model.User;
import model.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RestaurantCrud;
import repository.UserCrud;
import repository.VoteCrud;
import to.VoteTO;
import util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static util.ValidationUtil.checkNotFoundWithId;
import static util.ValidationUtil.isNotNew;

@Service("voteService")
public class VoteServiceImpl implements VoteService {
    private final Logger LOG = LoggerFactory.getLogger(VoteServiceImpl.class);

    private final UserCrud userCrud;

    private final RestaurantCrud restaurantCrud;

    private final VoteCrud voteCrud;

    @Autowired
    public VoteServiceImpl(UserCrud userCrud, RestaurantCrud restaurantCrud, VoteCrud voteCrud) {
        this.userCrud = userCrud;
        this.restaurantCrud = restaurantCrud;
        this.voteCrud = voteCrud;
    }

    @Override
    public Vote get(Integer voteId) {
        LOG.info("getId " + voteId);
        return checkNotFoundWithId(voteCrud.getById(voteId), voteId);
    }

    @Override
    public Vote save(Integer restaurantId, User user) {
        ValidationUtil.isItTooLateToVote();
        Vote createdVote = new Vote(user, restaurantCrud.getOne(restaurantId), LocalDateTime.now()
        );
        LOG.info("createdVote " + createdVote);
        return voteCrud.save(isNotNew(createdVote));
    }

    @Override
    public List<Vote> getSortedByDate(LocalDate date) {
        LOG.info("getSortedByDate");
        return voteCrud.getSortedByDate(date);
    }

    @Override
    public List<VoteTO> createVoteTOList(User user) {
        LOG.info("createVoteTOList");
        return userCrud.getVotesByUserId(user.getId())
                .stream()
                .map(vote -> new VoteTO(LocalDateTime.of(vote.getDate(), vote.getTime()), vote.getRestaurant().getId(), vote.getRestaurant().getName()))
                .collect(Collectors.toList());
    }
}
