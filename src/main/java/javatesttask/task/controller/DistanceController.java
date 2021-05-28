package javatesttask.task.controller;

import javatesttask.task.dto.DistanceAnswerDto;
import javatesttask.task.entity.CityEntity;
import javatesttask.task.entity.DistanceEntity;
import javatesttask.task.service.Calculable;
import javatesttask.task.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distance")
@RequiredArgsConstructor
public class DistanceController {

    private final EntityService<DistanceEntity> distancesService;

    private final Calculable<CityEntity, DistanceAnswerDto> calculationService;


    @GetMapping
    public List<DistanceEntity> findOnPage(@RequestParam("page") int num) {

        return distancesService.showEntityOnPage(num);
    }

    @GetMapping("/{id}")
    public DistanceEntity findById(@PathVariable Long id) {

        return null;
    }

    @PostMapping
    public DistanceAnswerDto calculate(@RequestParam("type") String type,
                                       @RequestBody CityEntity from,
                                       @RequestBody CityEntity to) {


        return calculationService.calculate(type, from, to);
    }

    @PostMapping("/byId")
    public DistanceAnswerDto calculateById(@RequestParam("type") String type,
                                           @RequestParam Long from,
                                           @RequestParam Long to) {

        return calculationService.calculateById(type, from, to);
    }
}
