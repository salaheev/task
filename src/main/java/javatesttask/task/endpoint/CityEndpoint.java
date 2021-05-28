package javatesttask.task.endpoint;

import javatesttask.task.entity.CityEntity;
import javatesttask.task.exception.NoUnitFoundException;
import javatesttask.task.repository.CitiesRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "city")
@RequiredArgsConstructor
public class CityEndpoint {

    private final CitiesRepository repository;

    @WriteOperation
    public Task existById(@Selector Long id) {

        CityEntity entity = repository.findById(id).orElse(null);

        if (entity != null) {
            return new Task("DONE", true);
        }

        return new Task("FAILURE", false);
    }

    @ReadOperation
    public CityEntity getById(@Selector Long id) {

        return repository.findById(id).orElseThrow(() -> new NoUnitFoundException("Unit not found"));
    }
}

@Data
@AllArgsConstructor
class Task {

    private String name;
    private boolean successful;

}
