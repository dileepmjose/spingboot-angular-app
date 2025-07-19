package com.java.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {
    private String provider;
    private String policyNumber;
    private LocalDate validTill;
}