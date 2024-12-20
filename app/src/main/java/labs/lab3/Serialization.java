package labs.lab3;

import java.io.*;
import java.util.List;

public interface Serialization<T> {
  T toEntity(String object) throws IOException;
  List<T> toEntityList(String objectCollection) throws IOException;
  String fromEntity(T object) throws IOException;
  String fromEntityList(List<T> objectCollection) throws IOException;
  List<T> toEntityListFromFile(String fileName) throws IOException;
  void fromEntityToFile(T object, String fileName) throws IOException;
  void fromEntityListToFile(List<T> objectCollection, String fileName) throws IOException;
}
