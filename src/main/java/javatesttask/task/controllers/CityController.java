package javatesttask.task.controllers;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.exceptions.NoUnitFoundException;
import javatesttask.task.services.EntityMapperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("city")
@AllArgsConstructor
public class CityController {

    private final EntityMapperService<CityEntity> mapperService;

    @GetMapping("{name}")
    public List<CityEntity> findCityByName(@PathVariable(name = "name") String name) {

        if(Objects.isNull(name)) {
            throw new RuntimeException("Please set city's name and try again");
        }

        final String handledName = handleCases(name);

        List<CityEntity> entities = mapperService.findByName(name);
        if (entities.isEmpty()) {
            throw new NoUnitFoundException("City with name: " + name + " not found");
        }
        return entities;
    }

    @GetMapping("/all")
    public List<CityEntity> findAllCities() {

        return mapperService.findAll();
    }


    private String handleCases(String name) {

        StringBuilder stringBuilder = new StringBuilder(name.toLowerCase());
        stringBuilder.replace(0,1, Character.toString(name.charAt(0)).toUpperCase());

        return stringBuilder.toString();
    }

}
