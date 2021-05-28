package javatesttask.task.util.converter;

import javatesttask.task.dto.CityDataDto;
import javatesttask.task.entity.CityEntity;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CityConvertor extends BaseDtoConvertor<CityEntity, CityDataDto> {

    public CityConvertor(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public CityDataDto toDto(CityEntity entity) {

        return super.getModelMapper().map(entity, CityDataDto.class);
    }
}
