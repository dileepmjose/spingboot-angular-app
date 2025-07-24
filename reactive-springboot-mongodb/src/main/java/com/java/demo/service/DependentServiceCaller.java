package com.java.demo.service;

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

    public Mono<Persons> callServiceGetAllPerson() {
        return webClient.get()
                .uri("http://localhost:8080/api/persons/get-all")
                .retrieve()
                .bodyToMono(Persons.class);


    }

    public String callServiceB(String inputFromA) {
        return webClient.post()
                .uri("http://localhost:8085/serviceB")
                .bodyValue(inputFromA)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Blocking call
    }

    public String callServiceC(String inputFromB) {
        return webClient.post()
                .uri("http://localhost:8086/serviceC")
                .bodyValue(inputFromB)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Blocking call
    }
}