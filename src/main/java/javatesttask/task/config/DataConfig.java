package javatesttask.task.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import javatesttask.task.util.calculator.CalculationType;
import javatesttask.task.util.calculator.Calculator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

    @Bean
    public Map<? extends CalculationType, Calculator<CalculationType>> calculatorMap() {

        return calculatorList.stream().collect(Collectors.toMap(Calculator::getType, calculator -> calculator, (a, b) -> b));
    }

    @Bean
    public Map<Integer, String> accuraciesMap() {
        Map<Integer, String> accMap = new HashMap<>();

        IntStream.rangeClosed(0, 6).forEachOrdered(i -> accMap.put(i, "%." + i + "f"));

        return accMap;
    }

}
