package javatesttask.task.service;

import javatesttask.task.dto.DistanceAnswerDto;
import javatesttask.task.dto.Transferable;
import javatesttask.task.entity.CityEntity;
import javatesttask.task.entity.DistanceEntity;
import javatesttask.task.exception.IllegalQueryParamException;
import javatesttask.task.exception.NoUnitFoundException;
import javatesttask.task.repository.CitiesRepository;
import javatesttask.task.repository.DistancesRepository;
import javatesttask.task.util.Precisions;
import javatesttask.task.util.calculator.CalculationType;
import javatesttask.task.util.calculator.Calculator;
import javatesttask.task.util.facade.DistanceFacade;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Getter
public class FirstCalculableService implements Calculable<CityEntity, Transferable> {

    @Value("${precision.level}")
    private Integer degree;

    @Qualifier("calculatorMap")
    private final Map<? extends CalculationType, Calculator<CalculationType>> calculatorMap;

    private final DistancesRepository distancesRepository;

    private final CitiesRepository citiesRepository;

    private final Precisions precisions;

    @Transactional
    @Override
    public DistanceAnswerDto calculate(String type, CityEntity from, CityEntity to) {

        if (Objects.isNull(type) || type.equals("")) {
            throw new IllegalQueryParamException("Empty type parameter, please select a correct one");
        }

        return saveAndReturn(type, from, to);
    }

    @Transactional
    @Override
    public DistanceAnswerDto calculateById(String type, Long from, Long to) {
        if (Objects.isNull(type) || type.equals("")) {
            throw new IllegalQueryParamException("Empty type parameter, please select a correct one");
        }

        if (from == 0 && to == 0) {
            throw new IllegalQueryParamException("No cities found with that id");
        }

        CityEntity dbFrom = citiesRepository.findById(from)
                .orElseThrow(() -> new NoUnitFoundException("City with id " + from + " not found."));

        CityEntity dbTo = citiesRepository.findById(to)
                .orElseThrow(() -> new NoUnitFoundException("City with id " + to + " not found."));

        return saveAndReturn(type, dbFrom, dbTo);
    }

    @Transactional
    @NonNull
    public DistanceAnswerDto saveAndReturn(String type, CityEntity dbFrom, CityEntity dbTo) {
        Double[] calculatedValue = doCalculate(type, dbFrom, dbTo);

        double[] doubles = precisions.preconize(calculatedValue, degree);

        DistanceEntity distanceEntity = new DistanceEntity(dbFrom, dbTo, doubles[0]);

        distancesRepository.save(distanceEntity);

        return DistanceFacade.of(distanceEntity, type).toDto();
    }


    public Double[] doCalculate(String type, CityEntity from, CityEntity to) {
        return calculatorMap
                .getOrDefault(CalculationType.valueOf(type.toUpperCase()), calculatorMap.get(CalculationType.ALL))
                .calculate(from, to);
    }
}
