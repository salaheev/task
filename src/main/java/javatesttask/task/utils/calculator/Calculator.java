package javatesttask.task.utils.calculator;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.utils.calcutation.CalculationType;

public interface Calculator <T extends CalculationType> {

    String calculate(CityEntity from, CityEntity to);

    T getType();

}
