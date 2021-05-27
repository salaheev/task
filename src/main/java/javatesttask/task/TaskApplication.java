package javatesttask.task;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.services.CitiesService;
import javatesttask.task.utils.calculator.CrowFlightCalculator;
import javatesttask.task.utils.calculator.MatrixCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TaskApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TaskApplication.class, args);

        var repository = context.getBean(CitiesService.class);


        var bean = context.getBean(MatrixCalculator.class);
        var bean2 = context.getBean(CrowFlightCalculator.class);

        var city1 = repository.findBy("Tashkent");
        var city2 = repository.findBy("Ufa");
        var city11 = (CityEntity) city1.getResponse();
        var city22 = (CityEntity) city2.getResponse();

        String calculate = bean.calculate(city11, city22);

        String calculate1 = bean2.calculate(city11, city22);

        System.out.println(calculate);

        System.out.println(calculate1);
    }
}
