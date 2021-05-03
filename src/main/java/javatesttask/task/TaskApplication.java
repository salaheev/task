package javatesttask.task;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);






















//        CsvAndJson2PojoHelper helper = context.getBean(CsvAndJson2PojoHelper.class);
//
//        CitiesRepository repo = context.getBean(CitiesRepository.class);
//
//        File file = new File("C:\\Users\\df.salaheev\\Desktop\\kotlin-go\\task\\src\\main\\resources\\data\\cities.json");
//
//        long count = helper.readJsonToObject(file)
//                .stream().map(p -> new CityEntity(p.getName(), p.getLatitude(), p.getLongitude()))
//                .map(repo::save)
//                .count();

//        repo.saveAll(cityEntities);
//        List<CityDto> collect = cities.stream().limit(1).collect(Collectors.toList());
//        CityDto cityDto = collect.get(0);
//
//        repo.save(new CityEntity(cityDto.getName(), cityDto.getLatitude(), cityDto.getLongitude()));
    }
}
