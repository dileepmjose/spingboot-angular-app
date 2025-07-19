package com.java.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRecord {
    private LocalDate date;
    private String serviceCenter;
    private List<String> workDone;
}