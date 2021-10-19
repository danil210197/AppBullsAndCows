package bullsAndCows.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating implements Comparable<Rating>{

    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private Integer record;

    public Rating(User user, int record){
        this.user = user;
        this.record = record;
    }

    @Override
    public int compareTo(Rating rating) {
        if(this.record < rating.getRecord())
            return 1;
        if(this.record > rating.getRecord())
            return -1;

        return 0;
    }
}
