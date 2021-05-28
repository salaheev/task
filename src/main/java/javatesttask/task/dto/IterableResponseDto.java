package javatesttask.task.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder(builderMethodName = "of")
public class IterableResponseDto<T> implements Serializable, Transferable{

    private boolean isIterable;
    private T response;

}
