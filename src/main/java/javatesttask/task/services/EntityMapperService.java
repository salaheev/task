package javatesttask.task.services;

import javatesttask.task.entity.CityEntity;

import java.util.List;

public interface EntityMapperService<T> {

    List<CityEntity> findByName(String name);

    List<T> findAll();


}
