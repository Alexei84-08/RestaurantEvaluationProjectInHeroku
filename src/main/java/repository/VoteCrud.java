package repository;

import model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteCrud extends JpaRepository<Vote, Integer> {

    @EntityGraph(Vote.GRAPH_WITH_USER_AND_RESTAURANT)
    @Query(name = Vote.GET_SORTED_BY_DATE)
    List<Vote> getSortedByDate(LocalDate date);

//    @EntityGraph(Vote.GRAPH_WITH_USER_AND_RESTAURANT)
    Vote getById(int id);
}
