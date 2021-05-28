package javatesttask.task.service;

import javatesttask.task.dto.IterableResponseDto;
import javatesttask.task.entity.CityEntity;
import javatesttask.task.exception.IllegalQueryParamException;
import javatesttask.task.exception.NoUnitFoundException;
import javatesttask.task.exception.PaginationException;
import javatesttask.task.repository.CitiesRepository;
import javatesttask.task.util.handler.CaseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CitiesService implements EntityService<CityEntity> {

    private final CitiesRepository citiesRepository;
    private final CaseHandler caseHandler;

    @Cacheable(value = "cityByName")
    @Override
    public IterableResponseDto<?> findBy(Object param) {

        if (Objects.isNull(param)) {
            throw new IllegalQueryParamException("Please set a city's name and try again");
        }

        List<? extends Serializable> entities = null;
        if (param instanceof String) {
            entities = citiesRepository.findByName(caseHandler.handle((String) param));
        }

        if (param instanceof Long) {
            CityEntity entity = citiesRepository.findById((Long) param).orElseThrow(() -> new NoUnitFoundException("City with param: " + param + " not found"));
            entities = Collections.singletonList(entity);
        }

        if (Objects.isNull(entities) || entities.isEmpty()) {
            throw new NoUnitFoundException("City with param: " + param + " not found");
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

    @Transactional
    @Override
    public CityEntity saveOne(CityEntity entity) {
        return citiesRepository.save(entity);
    }

    @Transactional
    @Override
    public CityEntity updateOneById(Long id, CityEntity entity) {

        CityEntity fromDb = null;

        if (!(id > 0) && Objects.nonNull(entity)) {

            fromDb = citiesRepository.findById(id)
                    .orElseThrow(() -> new NoUnitFoundException("City with id " + id + " not found."));

            if (!fromDb.equals(entity)) {
                return citiesRepository.save(entity);
            }
        }

        return entity;
    }

    private IterableResponseDto<?> handleResponse(List<? extends Serializable> entityList) {

        if (entityList.size() <= 1) {
            return IterableResponseDto.builder().response(entityList.get(0)).isIterable(false).build();
        }

        return IterableResponseDto.builder().response(entityList).isIterable(true).build();
    }
}
