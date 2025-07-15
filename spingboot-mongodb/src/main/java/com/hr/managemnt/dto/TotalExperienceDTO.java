package com.hr.managemnt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalExperienceDTO {
    private String employeeName;
    private String dept;
    private Integer totalExperienceYears;

}