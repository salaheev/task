package javatesttask.task.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Component
@RequiredArgsConstructor
public final class Precisions {

    @Qualifier("accuraciesMap")
    private final Map<Integer, String> accuraciesMap;

    public String toDimensional(Double digit, int precision) {
        if (precision == 0 || precision > 6) {
            throw new IllegalArgumentException("Precision size is from 0 until 6.");
        }

        return String.format(accuraciesMap.get(precision), digit);
    }

    public double[] preconize(Double[] calculatedValue, Integer degree) {
        return Arrays.stream(calculatedValue)
                .map(it -> this.toDimensional(it, degree))
                .map(it-> it.replace(",", "."))
                .mapToDouble(Double::valueOf)
                .toArray();
    }



}
