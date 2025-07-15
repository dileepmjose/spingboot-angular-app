package com.hr.managemnt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVehicleDTO {
    private String name;
    private String department;
    private List<String> hobbies;
    private List<VehicleSummary> vehiclesModel;
    private List<VehicleSummary> vehiclesRegistration;
}
