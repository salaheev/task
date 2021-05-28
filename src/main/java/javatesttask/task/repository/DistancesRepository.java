package javatesttask.task.repository;

import javatesttask.task.entity.DistanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistancesRepository extends JpaRepository<DistanceEntity, Long> {

}
