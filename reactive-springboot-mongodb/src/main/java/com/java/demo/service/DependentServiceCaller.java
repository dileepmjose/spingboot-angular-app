package com.java.demo.service;

import com.java.demo.model.Company;
import com.java.demo.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class DependentServiceCaller {

    @Autowired
    private WebClient webClient;

    public Flux<Persons> callServiceGetAllPerson() {
        return webClient.get()
                .uri("http://localhost:8080/api/persons/get-all")
                .retrieve()
                .bodyToFlux(Persons.class); // no subscribe here!
    }

    public Mono<Persons> callServicePersonById(String id) {
        return webClient.get()
                .uri("http://localhost:8080/api/persons/{id}", id)
                .retrieve()
                .bodyToMono(Persons.class);
    }

}