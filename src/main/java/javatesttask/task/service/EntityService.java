package javatesttask.task.service;

import javatesttask.task.dto.IterableResponseDto;

import java.util.List;

public interface EntityService<T> {

    IterableResponseDto<?> findBy(Object param);

    List<T> findAll();

    List<T> showEntityOnPage(int num);

    T saveOne(T entity);

    T updateOneById(Long id, T entity);
}
