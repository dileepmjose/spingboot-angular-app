package com.java.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {

    @Id
    private String id;

    private String personId;
    private Date orderDate;
    private Item[] items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private String itemName;
        private String quantity;
        private Double price;
    }
}
