package labs.lab3;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import labs.lab1.Student;

public class SerializerTest {
  private Student testStudent;
  private List<Student> testStudentList;
  private final String testFileName = "test.json";

  @BeforeEach
  public void setUp() {
    testStudent = new Student("John", "Doe", "johndoe@example.com", "C2");
    testStudentList = Arrays.asList(
      new Student("John", "Doe", "johndoe@example.com", "C2")
    );
  }

  @Test
    public void testJSONSerializer() throws IOException {
        Serialization<Student> jsonSerializer = new JSONSerializer<>(Student.class);
        performTests(jsonSerializer);
    }

    @Test
    public void testXMLSerializer() throws IOException {
        Serialization<Student> xmlSerializer = new XMLSerializer<>(Student.class);
        performTests(xmlSerializer);
    }

    @Test
    public void testYMLSerializer() throws IOException {
        Serialization<Student> ymlSerializer = new YAMLSerializer<>(Student.class);
        performTests(ymlSerializer);
    }

     private void performTests(Serialization<Student> serializer) throws IOException {
        // Test serialization to string
        String serialized = serializer.fromEntity(testStudent);
        assertNotNull(serialized, "Serialized output should not be null");

        // Test deserialization from string
        Student deserializedStudent = serializer.toEntity(serialized);
        assertEquals(testStudent, deserializedStudent, "Deserialized student should match the original");

        // Test list serialization
        String serializedList = serializer.fromEntityList(testStudentList);
        assertNotNull(serializedList, "Serialized list output should not be null");

        // Test deserialization of list
        List<Student> deserializedStudentList = serializer.toEntityList(serializedList);
        assertEquals(testStudentList, deserializedStudentList, "Deserialized list should match the original");

        // Test file serialization
        serializer.fromEntityToFile(testStudent, testFileName);
        File file = new File(testFileName);
        assertTrue(file.exists(), "File should be created");

        // Test file deserialization
        Student fileDeserializedStudent = serializer.toEntityListFromFile(testFileName).get(0);
        assertEquals(testStudent, fileDeserializedStudent, "Deserialized student from file should match the original");

        // Cleanup
        file.delete();
    }
}

