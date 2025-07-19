package com.java.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private String id;
    private String name;
    private int age;
    private String department;
    private List<Address> address;
    private List<String> skills;
    private List<String> hobbies;
    private List<Experience> experience;
}