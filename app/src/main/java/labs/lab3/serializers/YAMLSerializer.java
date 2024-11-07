package labs.lab3.serializers;

import labs.lab3.Serialization;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class YAMLSerializer<T> implements Serialization<T> {
  private final ObjectMapper mapper;
  private final Class<T> clazz;

  public YAMLSerializer(Class<T> clazz) {
    mapper = new ObjectMapper(new YAMLFactory());
    mapper.registerModule(new JavaTimeModule());
    this.clazz = clazz;
  }

  @Override
    public T toEntity(String object) throws IOException {
        return mapper.readValue(object, clazz);
    }

    @Override
    public List<T> toEntityList(String objectCollection) throws IOException {
        return mapper.readValue(objectCollection, new TypeReference<List<T>>() {});
    }

    @Override
    public String fromEntity(T object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    @Override
    public String fromEntityList(List<T> objectCollection) throws IOException {
        return mapper.writeValueAsString(objectCollection);
    }

    @Override
    public List<T> toEntityListFromFile(String fileName) throws IOException {
        return mapper.readValue(new File(fileName), new TypeReference<List<T>>() {});
    }

    @Override
    public void fromEntityToFile(T object, String fileName) throws IOException {
        mapper.writeValue(new File(fileName), object);
    }

    @Override
    public void fromEntityListToFile(List<T> objectCollection, String fileName) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), objectCollection);
    }
} 
