package bullsAndCows.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameStep {

    @Id
    @GeneratedValue
    @Column(name = "gameStep_id")
    private Long id;

    @Column(name = "stepUser", nullable = false)
    private int stepUser;

    @Column(name = "answer", nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private History history;

    public GameStep(int stepUser, String answer, History history) {
        this.stepUser = stepUser;
        this.answer = answer;
        this.history = history;
    }
}
