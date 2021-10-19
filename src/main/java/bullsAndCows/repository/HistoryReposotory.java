package bullsAndCows.repository;

import bullsAndCows.model.History;
import bullsAndCows.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryReposotory extends JpaRepository<History, Long> {

    @Query("select h from History h where h.isWin = :isWin")
    List<History> findByIsWin(@Param("isWin") boolean isWin);

    @Query("select h from History h where h.user = :user")
    List<History> findByUserAndStartDateSort(@Param("user") User user, Sort sort);

    @Query("select h from History h where h.user = :user")
    List<History> findByUserLastHistory(@Param("user") User user, Pageable pageable);

    @Query("select COUNT(h) from History h where h.user = :user_id")
    long getCountWinHistoryByUser(@Param("user_id") long userId);

    List<History> getHistoriesByUser(User user);

    @Query("SELECT COUNT(g) FROM GameStep g WHERE g.history=:history")
    long getCountGameSteps(@Param("history") History history);

    @Query("SELECT h FROM History h WHERE h.id=:history_id")
    History getByHistoryId(@Param("history_id") long id);

}
