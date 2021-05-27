package javatesttask.task.controllers;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.entity.DistanceEntity;
import javatesttask.task.services.Calculable;
import javatesttask.task.services.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distance")
@RequiredArgsConstructor
public class DistanceController {

    private final EntityService<DistanceEntity> distancesService;

    private final Calculable<CityEntity> calculationService;


    @GetMapping
    public List<DistanceEntity> findOnPage(@RequestParam("page") int num) {

        return distancesService.showEntityOnPage(num);
    }

    @GetMapping("/{id}")
    public DistanceEntity findById(@PathVariable Long id) {

        return null;

    }

    @PostMapping
    public String calculate(@RequestParam("type") String type,
                            @RequestBody CityEntity from,
                            @RequestBody CityEntity to) {


        return calculationService.calculate(type, from, to);
    }

    @PostMapping("/byId")
    public String calculateById(@RequestParam("type") String type,
                                @RequestParam Long from,
                                @RequestParam Long to) {

        return calculationService.calculateById(type, from, to);
    }


}