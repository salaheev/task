package javatesttask.task.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.File;

@Configuration
public class Config {

    @Value("${resource.path.cities}")
    private String resourcePath;

    @Bean
    public CsvMapper csvMapper() {
        return new CsvMapper();
    }

    @Bean
    @Primary
    public ObjectMapper mapper() {

        return new ObjectMapper();
    }

    @Bean
    public File resourceFile() {

        return new File(resourcePath);
    }


}
