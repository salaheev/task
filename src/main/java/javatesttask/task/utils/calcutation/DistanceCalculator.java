package javatesttask.task.utils.calcutation;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.utils.calculator.CalculationType;

public interface DistanceCalculator {

    String calculateBetween(CityEntity from, CityEntity to, CalculationType type);
}
