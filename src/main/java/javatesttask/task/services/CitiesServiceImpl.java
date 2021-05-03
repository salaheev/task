package javatesttask.task.services;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.repository.CitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitiesServiceImpl implements EntityMapperService<CityEntity> {


    @Value("${resource.path.cities}")
    private File file;

    private final CitiesRepository citiesRepository;

    @Override
    public List<CityEntity> findByName(String name) {

        return citiesRepository.findByName(name);
    }

    @Override
    public List<CityEntity> findAll() {

        return citiesRepository.findAll();
    }
}
