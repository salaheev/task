package javatesttask.task.utils;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.repository.CitiesRepository;
import javatesttask.task.utils.converter.ObjectConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Configuration
@ConditionalOnMissingBean(FlywayAutoConfiguration.class)
@RequiredArgsConstructor
public class DataFiller {

    @Value("${resource.path.cities}")
    private File file;

    private final CitiesRepository citiesRepository;

    private final ObjectConverter converterHelper;

    @PostConstruct
    public List<CityEntity> mapEntityAndFillDb() {

        return converterHelper.readJsonToObject(file)
                .stream()
                .map(p -> new CityEntity(p.getName(), p.getLatitude(), p.getLongitude()))
                .map(citiesRepository::save)
                .collect(toList());
    }
}

