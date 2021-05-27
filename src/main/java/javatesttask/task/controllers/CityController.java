package javatesttask.task.controllers;

import javatesttask.task.dto.IterableResponseDto;
import javatesttask.task.entity.CityEntity;
import javatesttask.task.services.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("city")
@AllArgsConstructor
public class CityController {

    private final EntityService<CityEntity> cityEntityService;

    @GetMapping("{name}")
    public IterableResponseDto<?> findCityByName(@PathVariable(name = "name") String name) {

        return cityEntityService.findBy(name);
    }

    @GetMapping("/all")
    public List<CityEntity> findAllCities() {

        return cityEntityService.findAll();
    }

    @GetMapping
    public List<CityEntity> findOnPage(@RequestParam("page") int page) {

        return cityEntityService.showEntityOnPage(page);
    }

}
