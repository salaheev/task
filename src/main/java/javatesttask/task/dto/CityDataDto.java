package javatesttask.task.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CityDataDto {

    private final String name;

    private final Double latitude;

    private final Double longitude;

}
