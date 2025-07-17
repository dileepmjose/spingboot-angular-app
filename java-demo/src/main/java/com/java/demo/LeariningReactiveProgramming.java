package com.java.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.util.function.Tuple3;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LeariningReactiveProgramming {


    public Mono<String> testmono(){
        return Mono.justOrEmpty("Hello  from Java Reactive").log();
    }

    public Flux<String> testFlux(){
        return Flux.just("Springbbot","AngularJS","MongoDB");
    }

    public Flux<String> testFluxFromList(){
        List<String> list = Arrays.asList("Java","C","Python","TypeScript");
        return Flux.fromIterable(list).log();
    }

    public Flux<String> testFluxFromArray(){
        String arr[] = {"car","jeep"};
        return  Flux.fromArray(arr);
    }

    public Flux<String> testFluxMap(){
        Flux<String> flux = Flux.just("Springbbot","AngularJS","MongoDB","Kafka");
        return flux.map(m->m.toUpperCase()).log();
    }

    public Flux<String> testFluxFlatMap(){
        Flux<String> flux = Flux.just("Springbbot","AngularJS","MongoDB","Kafka");
        return flux.flatMap(m-> Mono.just(m.toUpperCase()));
    }

    public Flux<String> testFluxSkip(){
        return Flux.just("Springbbot","AngularJS","MongoDB").skip(1);
    }

    public Flux<String> testFluxDealy(){
        Flux flux = Flux.just("Springbbot","AngularJS","MongoDB").delayElements(Duration.ofSeconds(1));
        return flux.skip(Duration.ofSeconds(2));
    }

 // Skip util while meeting // print 6,7,8
    public Flux<Integer> testFluxskipWhile(){
        Flux<Integer> flux = Flux.just(1, 3, 5, 6, 7, 8);
        return flux.skipWhile(i -> i % 2 != 0);


    }

    // Skip util while meeting // print 10..100
    public Flux<Integer> testFluxSkipUntil(){
        Flux<Integer> flux = Flux.range(0,100);
        return flux.skipUntil(i->i>10);

    }

    public Flux<Integer> testFluxConcat(){
        Flux<Integer> flux1 = Flux.range(0,10);
        Flux<Integer> flux2 = Flux.range(100,10);
        Flux<Integer> flux3 = Flux.range(1000,10);
        return Flux.concat(flux1,flux2,flux3);

    }

    // When there is dealy merge function subscribe from both Flux ,Where Conact Subscribe from Flux1 and then Flux2
    public Flux<Integer> testFluxMerge(){
        Flux<Integer> flux1 = Flux.range(0,10).delayElements(Duration.ofSeconds(1));
        Flux<Integer> flux2 = Flux.range(100,10).delayElements(Duration.ofSeconds(1));
        return Flux.merge(flux1,flux2);
    }

    // As soon as one flux complete exceution Reming element stop the Execution.See output(Tuple1...Tuple9 are avaiable)
    public Flux<Tuple3<Integer, Integer,Integer>> testFluxZip(){
        Flux<Integer> flux1 = Flux.range(0,5);
        Flux<Integer> flux2 = Flux.range(100,10);
        Flux<Integer> flux3 = Flux.range(1000,10);
        return Flux.zip(flux1,flux2,flux3);
    }

    public Mono<List<Integer>> testFluxCollectToList(){
        Flux<Integer> flux1 = Flux.range(0,5);
        return flux1.collectList();
    }


    public Flux<List<Integer>> testFluxBuffer(){
        Flux<Integer> flux1 = Flux.range(0,5).delayElements(Duration.ofSeconds(1)).log();
        return flux1.buffer(2);
       // return flux1.buffer(Duration.ofSeconds(1));   // check this
    }

    public Mono<Map<Integer,Integer>> testFluxCollectMap(){
        Flux<Integer> flux = Flux.range(0,5);
        return flux.collectMap(Integer->Integer,Integer->Integer*Integer);

    }

    public Flux<Integer> testFluxdoonEach(){
        Flux<Integer> flux = Flux.range(0,5);
        return flux.doOnEach(signal->{

            if(signal.equals(Signal.complete())){
                System.out.println("DONE"+signal);
            }else {
                System.out.println(signal.get());
            }
        });

    }

    public Flux<Integer> testFluxDispose(){
        Flux<Integer> flux = Flux.range(0,100).delayElements(Duration.ofSeconds(1));
        return flux.doOnCancel(()->System.out.println("Canceled"));

    }

 // Handle Exception and Continune flow
    public Flux<Integer> testFluxErrorHandling1(){
        Flux<Integer> flux = Flux.range(0,10)
        .map(i->{
          if(i == 5){
             throw new RuntimeException("Run time Exception");
          }
          return i;
        });
        return flux.onErrorContinue((throwable ,i)->{
            System.out.println("Handle error on here "+i);
        } );
    }

    // Covert on Error It Exceute Second Flux
    public Flux<Integer> testFluxErrorResume(){
        Flux<Integer> flux = Flux.range(0,10)
                .map(i->{
                    if(i == 5){
                        throw new RuntimeException("Run time Exception");
                    }
                    return i;
                });
        return flux.onErrorResume(Throwable -> Flux.range(100,10));
    }

    // Convert ArithmeticException  to RuntimeException,Help to Standazlise Exception
    public Flux<Integer> testFluxConvertException(){
        Flux<Integer> flux = Flux.range(0,10)
                .map(i->{
                    if(i == 5){
                        throw new ArithmeticException("  Exception");
                    }
                    return i;
                });
        return flux.onErrorMap(throwable -> new RuntimeException(throwable.getMessage()));
    }




    public static void main(String arg[]) throws InterruptedException {
        LeariningReactiveProgramming reactiveDemo= new LeariningReactiveProgramming();

        reactiveDemo.testFluxConvertException().subscribe(System.out::println);


   /*
       reactiveDemo.testFluxErrorResume().subscribe(System.out::println);
        reactiveDemo.testFluxErrorHandling2().subscribe(System.out::println);

        reactiveDemo.testFluxErrorHandling1().subscribe(System.out::println);

        Disposable dis = reactiveDemo.testFluxDispose().subscribe(System.out::println);
        Thread.sleep(10);
        dis.dispose();

        reactiveDemo.testFluxdoonEach().subscribe();
        reactiveDemo.testFluxCollectMap().subscribe(System.out::println);

        reactiveDemo.testFluxBuffer().subscribe(System.out::println);
        Thread.sleep(10000);

       reactiveDemo.testFluxCollectToList().subscribe(System.out::println);

        reactiveDemo.testFluxZip().subscribe(System.out::println);

        reactiveDemo.testFluxMerge().subscribe(System.out::println);
        Thread.sleep(10000);

        reactiveDemo.testFluxConcat().subscribe(System.out::println);
        reactiveDemo.testmono().subscribe(System.out::println);
        reactiveDemo.testFlux().subscribe(data -> System.out.println(data));
        reactiveDemo.testFluxFromList().subscribe(System.out::println);
        reactiveDemo.testFluxFromArray().subscribe(System.out::println);
        reactiveDemo.testFluxMap().subscribe(System.out::println);
        reactiveDemo.testFluxSkip().subscribe(System.out::println);
        reactiveDemo.testFluxDealy().subscribe(System.out::println);
        Thread.sleep(5000l);
        reactiveDemo.testFluxskipWhile().subscribe(System.out::println);
        reactiveDemo.testFluxSkipUntil().subscribe(System.out::println);

    */

    }
}
