package labs.lab3.serializers;

import labs.lab3.Serialization;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class XMLSerializer<T> implements Serialization<T> {
  private final ObjectMapper mapper;
    private final Class<T> clazz;

    public XMLSerializer(Class<T> clazz){
        mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());
        this.clazz = clazz;
    }

    @Override
    public T toEntity(String filePath) throws IOException {
       String content = new String(Files.readAllBytes(Paths.get(filePath)));
       return mapper.readValue(content, clazz);
    }

    @Override
    public List<T> toEntityList(String objectCollection) throws JsonProcessingException {
        return mapper.readValue(objectCollection, new TypeReference<List<T>>() {});
    }

    @Override
    public String fromEntity(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    @Override
    public String fromEntityList(List<T> objectCollection) throws JsonProcessingException {
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
        GenericListWrapper<T> wrapper = new GenericListWrapper<>(objectCollection);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), wrapper);
    }

    @JacksonXmlRootElement(localName = "List")
    public static class GenericListWrapper<T> {
        private static final String ITEM_NAME = "item";

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = ITEM_NAME)
        private List<T> items;

        public GenericListWrapper() {}

        public GenericListWrapper(List<T> items) {
            this.items = items;
        }

        public List<T> getItems() {
            return items;
        }

        public void setItems(List<T> items) {
            this.items = items;
        }
    }
}
