package com.java.demo.service;

import com.java.demo.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class OrchestratorService {

    @Autowired
    private DependentServiceCaller dependentServiceCaller;

    @Autowired
    private IndependentServiceCaller independentServiceCaller;

    public void executeAll() throws ExecutionException, InterruptedException {
        // 🔄 Sequential dependent calls

       Mono<Persons> person = dependentServiceCaller.callServiceGetAllPerson();

       System.out.println(person);


       /* String resB = dependentServiceCaller.callServiceB(resA);
        String resC = dependentServiceCaller.callServiceC(resB);

        System.out.println("Result from C: " + resC);

        // 🔀 Parallel independent calls
        CompletableFuture<String> futureE = independentServiceCaller.callServiceE();
        CompletableFuture<String> futureF = independentServiceCaller.callServiceF();
        CompletableFuture<String> futureG = independentServiceCaller.callServiceG();

        CompletableFuture.allOf(futureE, futureF, futureG).join();

        System.out.println("Result from E: " + futureE.get());
        System.out.println("Result from F: " + futureF.get());
        System.out.println("Result from G: " + futureG.get());*/
    }
}