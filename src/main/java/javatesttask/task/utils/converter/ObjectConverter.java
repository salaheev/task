package javatesttask.task.utils.converter;

import javatesttask.task.dto.FileToCityDto;

import java.io.File;
import java.util.List;

public interface ObjectConverter {

    List<FileToCityDto> readCities(File file);

    List<FileToCityDto> readJsonToObject(File file);

}
