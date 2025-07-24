package com.process.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("persons")
public class Person {

    @Id
    public String id;

    private int index;
    private String name;
    private boolean isActive;
    private String registered;
    private int age;
    private String gender;
    private String eyeColor;
    private String favoriteFruit;
    private List<Company> company;
    private List<String> tags;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Company {
        private String title;
        private String email;
        private String phone;
        private Location location;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Location {
        private String city;
        private String country;
    }
}
