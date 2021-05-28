package javatesttask.task.util.converter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
@Getter
public abstract class BaseDtoConvertor<E, R> {

    private final ModelMapper modelMapper;

    public abstract R toDto(E entity);

}
