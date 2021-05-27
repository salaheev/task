package javatesttask.task.services;

import javatesttask.task.dto.IterableResponseDto;
import javatesttask.task.entity.CityEntity;
import javatesttask.task.entity.DistanceEntity;
import javatesttask.task.repository.DistancesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistancesService implements EntityMapperService<DistanceEntity> {

    private final DistancesRepository distancesRepository;

    private final EntityMapperService<CityEntity> cityService;

    @Override
    public IterableResponseDto<?> findBy(String param) {
        return null;
    }

    @Override
    public List<DistanceEntity> findAll() {
        return null;
    }

    @Override
    public List<DistanceEntity> showEntityOnPage(int num) {

        distancesRepository.findAll(PageRequest.of(num - 1, 15));
        return null;
    }
}
