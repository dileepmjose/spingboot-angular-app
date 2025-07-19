package com.java.demo;

import org.springframework.boot.SpringApplication;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ReactiveBackPressure {


    private Flux<Long> idealCaseWithNoOverflow(){
     return Flux.range(0,1000000).log().concatMap(x-> Mono.delay(Duration.ofMillis(10)));
    }

    //BackFire Scenario
    private Flux<Long> CreateOverFlow(){
        return Flux.interval(Duration.ofMillis(1)).log().concatMap(x->Mono.delay(Duration.ofMillis(100)));
    }

    //BackFire Drop
    private Flux<Long> DroponBackPressure(){
        return Flux.interval(Duration.ofMillis(1)).onBackpressureDrop().concatMap(x->Mono.delay(Duration.ofMillis(100)).
                thenReturn(x).doOnNext(a->System.out.println(x)));
    }

    //BackFire Buffer
    private Flux<Long> BufferBackPressure(){
        return Flux.interval(Duration.ofMillis(1)).
                onBackpressureBuffer(50, BufferOverflowStrategy.DROP_LATEST).
                concatMap(x->Mono.delay(Duration.ofMillis(100)).
                thenReturn(x).doOnNext(a->System.out.println(x)));
    }





    public static void main(String[] args) {
        ReactiveBackPressure  reactiveBackPressure = new ReactiveBackPressure();



        //reactiveBackPressure.BufferBackPressure().blockLast();

      //  reactiveBackPressure.DroponBackPressure().blockLast();

       // reactiveBackPressure.idealCaseWithNoOverflow().blockLast();


        //reactiveBackPressure.CreateOverFlow().blockLast();


    }
}
