package com.java.demo.controller;

import com.java.demo.model.Item;
import com.java.demo.model.Order;
import com.java.demo.model.Persons;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;


    @PostMapping("/create")
    public ResponseEntity<Mono<Persons>>savePerson(@RequestBody Persons person) {
        return ResponseEntity.ok( reactiveMongoTemplate.save(person).log());
    }

    @GetMapping("/get-all")
    public ResponseEntity<Flux<Persons>> getAll() {
        Query query= new Query();
        return ResponseEntity.ok(getALlPerson());
    }


    @GetMapping("/get-all-order-summary")
    public ResponseEntity<Mono<Map<String,Double>>> getAllOrderSummary() {
        Mono<Map<String,Double>> map = getOrderSummary();
        Flux<Persons> personFlux = getALlPerson();
        return ResponseEntity.ok(map);
    }

    public Flux<Persons> getALlPerson(){
        return reactiveMongoTemplate.findAll(Persons.class);
    }


    /* Retrieves a summary of total order amounts for all persons.

     */

    public Mono<Map<String,Double>> getOrderSummary(){
        Map<String,Double> map = new HashMap<>();
        return getALlPerson().flatMap(person-> Mono.zip(Mono.just(person),getOrderbyPersonId(person.getId()))).
                collectMap(tuple2 -> tuple2.getT1().getName(),tuple2->tuple2.getT2());

    }

    // Calculates the total price of all items across all orders associated with a given personId.
    public Mono<Double> getOrderbyPersonId(String personId){
        Criteria criteria = Criteria.where("personId").is(new ObjectId(personId));
        Query query = Query.query(criteria);
        return reactiveMongoTemplate.find(query, Order.class) // returns Flux<Order>
                .flatMap(order -> Flux.fromArray(order.getItems())) // flatten all items from all orders
                .map(Item::getPrice)                                // extract price
                .reduce(0d, Double::sum);
    }

}
