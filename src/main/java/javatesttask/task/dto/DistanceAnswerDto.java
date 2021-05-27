package javatesttask.task.dto;

import javatesttask.task.utils.calculator.CalculationType;
import lombok.*;

@Data
public class DistanceAnswerDto {

    private final Double distance;

    private final CalculationType calculationType;

}
