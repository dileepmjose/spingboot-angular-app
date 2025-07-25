package com.java.demo.dto;

import lombok.Data;

@Data
public class AvEyeAge {
    String _id;
    String avgAge;

    @Override
    public String toString() {
        return "AvEyeAge{" +
                "_id='" + _id + '\'' +
                ", avgAge='" + avgAge + '\'' +
                '}';
    }
}
