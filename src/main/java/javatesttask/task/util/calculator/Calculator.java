package javatesttask.task.util.calculator;

import javatesttask.task.entity.CityEntity;

public interface Calculator<T extends CalculationType> {

    Double[] calculate(CityEntity from, CityEntity to);

    T getType();

}
