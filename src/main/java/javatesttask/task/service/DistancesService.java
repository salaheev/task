package javatesttask.task.service;

import javatesttask.task.dto.DistanceAnswerDto;
import javatesttask.task.dto.IterableResponseDto;
import javatesttask.task.dto.Transferable;
import javatesttask.task.entity.CityEntity;
import javatesttask.task.entity.DistanceEntity;
import javatesttask.task.exception.IllegalQueryParamException;
import javatesttask.task.exception.MethodException;
import javatesttask.task.exception.NoUnitFoundException;
import javatesttask.task.repository.CitiesRepository;
import javatesttask.task.repository.DistancesRepository;
import javatesttask.task.util.calculator.CalculationType;
import javatesttask.task.util.calculator.Calculator;
import javatesttask.task.util.facade.DistanceFacade;
import javatesttask.task.util.handler.CaseHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DistancesService implements EntityService<DistanceEntity>, Calculable<CityEntity, Transferable> {

    private final DistancesRepository distancesRepository;

    private final CitiesRepository citiesRepository;

    private final CaseHandler caseHandler;

    @Qualifier("calculatorMap")
    private final Map<CalculationType, Calculator<CalculationType>> calculatorMap;

    private final Calculator<CalculationType> DEFAULT_ALL_CALCULATOR = calculatorMap.get(CalculationType.ALL);

    @Override
    public IterableResponseDto<?> findBy(Object param) {

        if (Objects.isNull(param)) {
            throw new IllegalQueryParamException("Please set a city's name and try again");
        }

        List<DistanceEntity> entities = null;

        if (param instanceof String) {
            String handledStr = caseHandler.handle((String) param);
            entities = distancesRepository.findByName(caseHandler.handle(handledStr));
        }

        if (param instanceof Long) {
            var entity = distancesRepository.findById((Long) param).orElseThrow(() -> new NoUnitFoundException("City with param: " + param + " not found"));
            entities = Collections.singletonList(entity);
        }

        if (Objects.isNull(entities) || entities.isEmpty()) {
            throw new NoUnitFoundException("City with param: " + param + " not found");
        }

        return handleResponse(entities);
    }

    @Override
    public List<DistanceEntity> findAll() {
        return distancesRepository.findAll();
    }

    @Override
    public List<DistanceEntity> showEntityOnPage(int num) {

        return distancesRepository.findAll(PageRequest.of(num - 1, 15)).getContent();
    }

    @Transactional
    @Override
    public DistanceEntity saveOne(DistanceEntity entity) {

        return distancesRepository.save(entity);
    }

    @Override
    public DistanceEntity updateOneById(Long id, DistanceEntity entity) {

        throw new MethodException("Method is not implemented");
    }


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

    @NonNull
    private DistanceAnswerDto saveAndReturn(String type, CityEntity dbFrom, CityEntity dbTo) {
        var calculatedValue = doCalculate(type, dbFrom, dbTo);

        DistanceEntity distanceEntity = new DistanceEntity(dbFrom, dbTo, calculatedValue[0]);

        distancesRepository.save(distanceEntity);

        return DistanceFacade.of(distanceEntity, type).toDto();
    }


    private Double[] doCalculate(String type, CityEntity from, CityEntity to) {
        return calculatorMap.getOrDefault(CalculationType.valueOf(type), DEFAULT_ALL_CALCULATOR).calculate(from, to);
    }


    private IterableResponseDto<?> handleResponse(List<? extends Serializable> entityList) {

        if (entityList.size() <= 1) {
            return IterableResponseDto.builder().response(entityList.get(0)).isIterable(false).build();
        }

        return IterableResponseDto.builder().response(entityList).isIterable(true).build();
    }
}