package javatesttask.task.services;

import javatesttask.task.dto.IterableResponseDto;
import javatesttask.task.entity.CityEntity;
import javatesttask.task.exceptions.NoUnitFoundException;
import javatesttask.task.exceptions.PaginationException;
import javatesttask.task.repository.CitiesRepository;
import javatesttask.task.utils.calculator.CalculationType;
import javatesttask.task.utils.calculator.Calculator;
import javatesttask.task.utils.handler.CaseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CitiesService implements EntityMapperService<CityEntity> {

    private final CitiesRepository citiesRepository;
    private final CaseHandler caseHandler;

    @Qualifier("calculatorMap")
    private final Map<CalculationType, Calculator<CalculationType>> calculatorMap;

    @Cacheable(value = "cityByName")
    @Override
    public IterableResponseDto<?> findBy(String param) {

        if (Objects.isNull(param)) {
            throw new RuntimeException("Please set city's name and try again");
        }

        List<CityEntity> entities = citiesRepository.findByName(caseHandler.handle(param));
        if (entities.isEmpty()) {
            throw new NoUnitFoundException("City with name: " + param + " not found");
        }

        return handleResponse(entities);
    }

    @Override
    public List<CityEntity> findAll() {

        return citiesRepository.findAll();
    }

    @Cacheable(value = "cityOnPage")
    @Override
    public List<CityEntity> showEntityOnPage(int num) {

        List<CityEntity> cityOnPage = citiesRepository.findAll(PageRequest.of(num - 1, 25)).getContent();

        if (num < 0) {
            throw new PaginationException("Cannot show entry on the page " + num + ". Page number should be more than 0!");
        }
        if (cityOnPage.isEmpty()) {
            throw new NoUnitFoundException("No units found. Recheck your query.");
        } else {
            return cityOnPage;
        }
    }

    private IterableResponseDto<?> handleResponse(List<CityEntity> entityList) {

        if (entityList.size() <= 1) {
            return IterableResponseDto.of().response(entityList.get(0)).isIterable(false).build();
        }

        return IterableResponseDto.of().response(entityList).isIterable(true).build();
    }
}
