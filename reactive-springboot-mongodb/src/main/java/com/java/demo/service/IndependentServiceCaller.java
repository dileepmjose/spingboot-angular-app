package com.java.demo.service;

import com.java.demo.dto.AvEyeAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class IndependentServiceCaller {

    @Autowired
    private WebClient webClient;


    @Async
    public CompletableFuture<AvEyeAge[]> getAvgAgeEmployee() {
        return webClient.get()
                .uri("http://localhost:8080/api/persons/avg-age-by-eyeColor")
                .retrieve()
                .bodyToMono(AvEyeAge[].class)
                .toFuture(); // Converts Mono to CompletableFuture
    }


    @Async
    public CompletableFuture<String[]> getDistinctCountry() {
        return webClient.get()
                .uri("http://localhost:8080/api/persons/countries")
                .retrieve()
                .bodyToMono(String[].class)
                .toFuture(); // Converts Mono to CompletableFuture
    }

}