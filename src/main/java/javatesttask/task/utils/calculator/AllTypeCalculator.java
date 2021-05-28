package javatesttask.task.utils.calculator;

import javatesttask.task.entity.CityEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllTypeCalculator implements Calculator<CalculationType> {

    @Getter
    private final CalculationType calculationType = CalculationType.ALL;

    private final List<Calculator<CalculationType>> calculatorList;

    @Override
    public Double[] calculate(CityEntity from, CityEntity to) {


        Double[] doubles = new Double[calculatorList.size()];

        for (int i = 0; i < doubles.length; i++) {

            doubles[i] = calculatorList.get(i).calculate(from, to)[0];

        }


//        List<Double> result = new ArrayList<>(calculatorList.size());
//
//        for (Calculator<CalculationType> calculator : calculatorList) {
//
//            result.add(calculator.calculate(from, to)[0]);
//        }

        return doubles;
    }

    @Override
    public CalculationType getType() {

        return calculationType;
    }
}
