package javatesttask.task.utils.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import javatesttask.task.dto.FileToCityDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CsvJsonToPojoConverter implements ObjectConverter {

    @Qualifier("csvMapper")
    private final CsvMapper csvMapper;

    private final ObjectMapper objectMapper;

    public List<FileToCityDto> readCities(File file) {
        try {
            MappingIterator<FileToCityDto> iterator = csvMapper.readerWithSchemaFor(FileToCityDto.class).readValues(file);
            return iterator.readAll();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<FileToCityDto> readJsonToObject(File file) {
        try {
            return objectMapper.readValue(file, new TypeReference<List<FileToCityDto>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
