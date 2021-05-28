package javatesttask.task.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import javatesttask.task.util.calculator.Calculator;
import javatesttask.task.util.calculator.CalculationType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class DataConfig {

    private final List<Calculator<CalculationType>> calculatorList;

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

    @Bean
    public Map<? extends CalculationType, Calculator<CalculationType>> calculatorMap() {

        Map<CalculationType, Calculator<CalculationType>> calculatorMap = new HashMap<>();

        calculatorList.forEach(calculator -> calculatorMap.put(calculator.getType(), calculator));
        return calculatorMap;
    }

}
