package javatesttask.task.utils.calcutation;

import javatesttask.task.entity.CityEntity;

public interface DistanceCalculator {

    String calculateBetween(CityEntity from, CityEntity to, CalculationType type);
}
