package com.java.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    private String id;
    private String type;
    private String brand;
    private String model;
    private String registration;
    private String employeeId;
    private Insurance insurance;
    private List<Driver> drivers;
    private List<ServiceRecord> serviceRecords;
}
