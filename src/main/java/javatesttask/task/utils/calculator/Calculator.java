package javatesttask.task.utils.calculator;

import javatesttask.task.entity.CityEntity;

public interface Calculator <T extends CalculationType> {

    String calculate(CityEntity from, CityEntity to);

    T getType();

}
