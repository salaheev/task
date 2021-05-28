package javatesttask.task.utils.calculator;

import javatesttask.task.entity.CityEntity;

import java.util.HashMap;
import java.util.Map;

public interface Calculator<T extends CalculationType> {

    Map<Integer, String> ACCURACIES_MAP = new HashMap<>() {{

        for (int i = 0; i < 6; i++) {

            ACCURACIES_MAP.put(i, "%." + i + "f");
        }

    }};

    static String toDimensional(Double digit, int precision) {
        if (precision == 0 || precision > 6) {
            throw new IllegalArgumentException("Precision size is from 0 until 6.");
        }

        return String.format(ACCURACIES_MAP.get(precision), digit);
    }

    Double[] calculate(CityEntity from, CityEntity to);

    T getType();

}
