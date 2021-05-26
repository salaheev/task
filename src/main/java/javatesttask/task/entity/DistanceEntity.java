package javatesttask.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "distances")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DistanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = CityEntity.class)
    private CityEntity from;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = CityEntity.class)
    private CityEntity to;

    private Double distance;


}
