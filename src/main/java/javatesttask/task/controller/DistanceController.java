package javatesttask.task.controller;

import javatesttask.task.dto.Transferable;
import javatesttask.task.entity.CityEntity;
import javatesttask.task.entity.DistanceEntity;
import javatesttask.task.service.Calculable;
import javatesttask.task.service.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/distance")
@AllArgsConstructor
public class DistanceController {

    private final EntityService<DistanceEntity> distancesService;

    private final Calculable<CityEntity, Transferable> calculationService;

    @GetMapping
    public List<DistanceEntity> findOnPage(@RequestParam("page") int num) {

        return distancesService.showEntityOnPage(num);
    }

    @GetMapping("/{id}")
    public DistanceEntity findById(@PathVariable Long id) {

        return null;
    }

    @PostMapping
    public Transferable calculate(@RequestParam("type") String type,
                                       @RequestBody CityEntity from,
                                       @RequestBody CityEntity to) {


        return calculationService.calculate(type, from, to);
    }

    @PostMapping("/byId")
    public Transferable calculateById(@RequestParam("type") String type,
                                           @RequestParam Long from,
                                           @RequestParam Long to) {

        if(Objects.isNull(type) && Objects.isNull(from) && Objects.isNull(to)) throw new NullPointerException();

        return calculationService.calculateById(type, from, to);
    }
}
