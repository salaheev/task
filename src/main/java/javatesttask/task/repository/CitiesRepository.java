package javatesttask.task.repository;

import javatesttask.task.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CitiesRepository extends JpaRepository<CityEntity, Long> {


    List<CityEntity>findByName(String name);



}
