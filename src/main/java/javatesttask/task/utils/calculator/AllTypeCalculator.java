package javatesttask.task.utils.calculator;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.utils.calcutation.CalculationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllTypeCalculator implements Calculator<CalculationType> {

    @Getter
    private final CalculationType calculationType = CalculationType.ALL;

    private final List<Calculator<CalculationType>> calculatorList;

    @Override
    public String calculate(CityEntity from, CityEntity to) {

        List<String> result = new ArrayList<>(calculatorList.size());

        for (Calculator<CalculationType> calculator : calculatorList) {

            result.add(calculator.calculate(from, to));
        }

        return String.join(", ", result);
    }

    @Override
    public CalculationType getType() {

        return calculationType;
    }
}
