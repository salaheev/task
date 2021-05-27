package javatesttask.task.utils.calculator;

import javatesttask.task.entity.CityEntity;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public interface Calculator<T extends CalculationType> {

    static String[] toDimensional(Double digit) {

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setRoundingMode(RoundingMode.CEILING);

        return decimalFormat
                .format(digit)
                .split(",");
    }

    String calculate(CityEntity from, CityEntity to);

    T getType();

}
