package labs.lab3;

import labs.lab3.serializers.JSONSerializer;
import labs.lab3.serializers.XMLSerializer; 
import labs.lab3.serializers.YAMLSerializer;

import java.time.LocalDate;
import java.io.File;
import java.io.IOException;

import labs.lab1.Course;
import labs.lab1.Language;

public class Main {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2024, 1, 10);
        LocalDate endDate = LocalDate.of(2024, 3, 20);

        Course course = new Course("English Advanced", Language.ENGLISH, "C2", startDate, endDate, 300.0);

        File jsonFile = new File("course.json");
        File xmlFile = new File("course.xml");
        File yamlFile = new File("course.yaml");

        JSONSerializer<Course> jsonSerializer = new JSONSerializer<>(Course.class);
        try {
            jsonSerializer.fromEntityToFile(course, jsonFile.getPath());
            System.out.println("Course serialized to JSON.");

            Course deserializedJsonCourse = jsonSerializer.toEntity(jsonFile.getPath());
            System.out.println("Deserialized JSON Course: " + deserializedJsonCourse);
        } catch (IOException e) {
            System.out.println("Error with JSON serialization: " + e.getMessage());
        }


        XMLSerializer<Course> xmlSerializer = new XMLSerializer<>(Course.class);
        try {
            xmlSerializer.fromEntityToFile(course, xmlFile.getPath());
            System.out.println("Course serialized to XML.");

            Course deserializedXmlCourse = xmlSerializer.toEntity(xmlFile.getPath());
            System.out.println("Deserialized XML Course: " + deserializedXmlCourse);
        } catch (IOException e) {
            System.out.println("Error with XML serialization: " + e.getMessage());
        }


        YAMLSerializer<Course> yamlSerializer = new YAMLSerializer<>(Course.class);
        try {
            yamlSerializer.fromEntityToFile(course, yamlFile.getPath());
            System.out.println("Course serialized to YAML.");

            Course deserializedYamlCourse = yamlSerializer.toEntity(yamlFile.getPath());
            System.out.println("Deserialized YAML Course: " + deserializedYamlCourse);
        } catch (IOException e) {
            System.out.println("Error with YAML serialization: " + e.getMessage());
        }
    }
}
