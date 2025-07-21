package com.batch.demo.model;

import lombok.Data;

@Data
public class Person {
    private int index;
    private String name;
    private boolean isActive;
    private String registered;
    private int age;
    private String gender;
    private String eyeColor;
    private String favoriteFruit;
    private Company company;

    @Data
    public static class Company {
        private String title;
        private String email;
        private String phone;
        private Location location;

        @Data
        public static class Location {
            private String country;
            private String address;
        }
    }
}