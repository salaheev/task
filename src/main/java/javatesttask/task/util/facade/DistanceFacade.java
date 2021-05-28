package javatesttask.task.util.facade;

import javatesttask.task.dto.DistanceAnswerDto;
import javatesttask.task.entity.DistanceEntity;
import javatesttask.task.util.calculator.CalculationType;
import lombok.Value;

@Value(staticConstructor = "of")
public class DistanceFacade {

    DistanceEntity entity;
    String calculationType;

    public DistanceAnswerDto getDto() {

        return DistanceAnswerDto.of(entity.getFrom(), entity.getTo(), entity.getDistance(), CalculationType.valueOf(this.calculationType));
    }
}
