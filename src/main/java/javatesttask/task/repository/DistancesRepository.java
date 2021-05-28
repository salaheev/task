package javatesttask.task.repository;

import javatesttask.task.entity.DistanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistancesRepository extends JpaRepository<DistanceEntity, Long> {

    List<DistanceEntity> findByName(String name);

}
