package javatesttask.task.service;

import javatesttask.task.dto.IterableResponseDto;
import javatesttask.task.entity.DistanceEntity;
import javatesttask.task.exception.MethodException;
import javatesttask.task.repository.CitiesRepository;
import javatesttask.task.repository.DistancesRepository;
import javatesttask.task.util.calculator.CalculationType;
import javatesttask.task.util.calculator.Calculator;
import javatesttask.task.util.handler.CaseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DistancesService implements EntityService<DistanceEntity> {

    private final DistancesRepository distancesRepository;

    private final CitiesRepository citiesRepository;

    private final CaseHandler caseHandler;

    @Qualifier("calculatorMap")
    private final Map<CalculationType, Calculator<CalculationType>> calculatorMap;

    @Override
    public IterableResponseDto<?> findBy(Object param) {

//        if (Objects.isNull(param)) {
//            throw new IllegalQueryParamException("Please set a city's name and try again");
//        }
//
//        List<DistanceEntity> entities = null;
//
//        if (param instanceof String) {
//            String handledStr = caseHandler.handle((String) param);
//            entities = distancesRepository.findByName(caseHandler.handle(handledStr));
//        }
//
//        if (param instanceof Long) {
//            var entity = distancesRepository.findById((Long) param).orElseThrow(() -> new NoUnitFoundException("City with param: " + param + " not found"));
//            entities = Collections.singletonList(entity);
//        }
//
//        if (Objects.isNull(entities) || entities.isEmpty()) {
//            throw new NoUnitFoundException("City with param: " + param + " not found");
//        }

//        return handleResponse(entities);
        return null;
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

    private IterableResponseDto<?> handleResponse(List<? extends Serializable> entityList) {

        if (entityList.size() <= 1) {
            return IterableResponseDto.builder().response(entityList.get(0)).isIterable(false).build();
        }

        return IterableResponseDto.builder().response(entityList).isIterable(true).build();
    }
}