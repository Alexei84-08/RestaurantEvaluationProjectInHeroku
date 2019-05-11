package service;

import model.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import to.VoteTO;
import util.exception.NotFoundException;
import util.exception.TooLateToVote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static testdata.RestaurantTestData.MUMU;
import static testdata.UserTestData.USER_JOHN_DOE;
import static testdata.UserTestData.USER_USER;
import static testdata.VoteTestData.*;
import static util.TimeUtil.setVoteFinishTime;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService voteService;

    @Test
    void get() {
        assertThat(voteService.get(VOTE130.getId())).isEqualToIgnoringGivenFields(VOTE130, "user", "restaurant");
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> voteService.get(NO_VOTE));
    }

    @Test
    @Transactional
    void save() {
        setVoteFinishTime(LocalTime.now().plusMinutes(1));
        Vote created = voteService.save(MUMU.getId(), USER_JOHN_DOE);
        Vote actual = voteService.get(created.getId());
        assertThat(actual).isEqualToIgnoringGivenFields(created);
    }

    @Test
    void createLater() {
        setVoteFinishTime(LocalTime.now().minusMinutes(1));
        assertThrows(TooLateToVote.class, () ->
                voteService.save(MUMU.getId(), USER_JOHN_DOE));
    }

    @Test
    void getSortedByDate() {
        assertMatch(voteService.getSortedByDate(LocalDate.now()), VOTES_TODAY);
    }

    @Test
    void createVoteTOList() {
        List<VoteTO> voteTOList = voteService.createVoteTOList(USER_USER);
        assertThat(voteTOList.size()).isEqualTo(4);
        assertThat(voteTOList).isEqualTo(VOTES_TO_USER_USER);
    }
}