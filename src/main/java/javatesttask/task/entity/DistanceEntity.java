package javatesttask.task.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "distances")
@NoArgsConstructor
@Data
public class DistanceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(orphanRemoval = true, targetEntity = CityEntity.class)
    private CityEntity from;

    @OneToOne(orphanRemoval = true, targetEntity = CityEntity.class)
    private CityEntity to;

    private Double distance;

    public DistanceEntity(CityEntity from, CityEntity to, Double distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}
