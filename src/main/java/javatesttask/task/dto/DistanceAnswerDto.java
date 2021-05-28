package javatesttask.task.dto;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.util.calculator.CalculationType;
import lombok.*;

@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = false)
public class DistanceAnswerDto implements Transferable {

    CityEntity from;

    CityEntity to;

    Double[] distance;

    CalculationType calculationType;

}
