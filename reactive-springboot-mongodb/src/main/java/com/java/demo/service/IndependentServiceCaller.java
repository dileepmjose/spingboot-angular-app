package com.java.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Service
public class IndependentServiceCaller {

    @Autowired
    private WebClient webClient;

    @Async
    public CompletableFuture<String> callServiceE() {
        return webClient.get()
                .uri("http://localhost:8081/serviceE")
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();
    }

    @Async
    public CompletableFuture<String> callServiceF() {
        return webClient.get()
                .uri("http://localhost:8082/serviceF")
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();
    }

    @Async
    public CompletableFuture<String> callServiceG() {
        return webClient.get()
                .uri("http://localhost:8083/serviceG")
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();
    }
}