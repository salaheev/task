package javatesttask.task.services;

import javatesttask.task.dto.IterableResponseDto;

import java.util.List;

public interface EntityMapperService<T> {

    IterableResponseDto<?> findByName(String name);

    List<T> findAll();

    List<T> showEntityOnPage(int num);
}
