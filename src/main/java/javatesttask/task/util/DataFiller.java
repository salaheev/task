package javatesttask.task.util;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.repository.CitiesRepository;
import javatesttask.task.util.converter.ObjectConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@ConditionalOnBean(FlywayAutoConfiguration.class)
@RequiredArgsConstructor
public class DataFiller implements ApplicationRunner {

    @Value("${resource.path.cities}")
    private File file;

    private final CitiesRepository citiesRepository;

    private final ObjectConverter converterHelper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        converterHelper.readJsonToObject(file)
                .stream()
                .map(p -> new CityEntity(p.getName(), p.getLatitude(), p.getLongitude()))
                .forEach(citiesRepository::save);
    }
}

