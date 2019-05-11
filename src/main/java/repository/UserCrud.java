package repository;

import model.User;
import model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface UserCrud extends JpaRepository<User, Integer> {

    @EntityGraph(User.GRAPH_WITH_VOTE_HISTORY_AND_ROLES)
    @Query(name = User.GET_BY_ID)
    User get(Integer id);

    @EntityGraph(User.GRAPH_WITH_VOTE_HISTORY_AND_ROLES)
    @Query(name = User.BY_LOGIN)
    User getByLogin(String login);

    @EntityGraph(Vote.GRAPH_WITH_USER_AND_RESTAURANT)
    @Query(name = User.VOTES)
    List<Vote> getVotesByUserId(Integer userId);
}
