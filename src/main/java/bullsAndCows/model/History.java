package bullsAndCows.model;

import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class History {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Id
    @GeneratedValue
    @Column(name = "history_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private int number;

    @Column(name = "startDate", nullable = false)
    private Calendar startDate;

    @Column(name = "endDate")
    private Calendar endDate;

    @Column(name = "steps")
    private long steps;

    @Column(name = "isWin")
    private GameResultEnum isWin;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameStep> gameStep = new ArrayList<>();


    public String getStartDateGame(){
        return startDate != null
                ? formatter.format(startDate.getTime())
                : "";
    }

    public String getEndDateGame(){
        return endDate != null
                ? formatter.format(endDate.getTime())
                : "";
    }

    public boolean isGameOver(){
        return isWin == GameResultEnum.YES;
    }

    public History(int number, Calendar startDate, Calendar endDate, int steps, GameResultEnum isWin, User user, List<GameStep> gameStep) {
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
        this.steps = steps;
        this.isWin = isWin;
        this.user = user;
        this.gameStep = gameStep;
    }


}
