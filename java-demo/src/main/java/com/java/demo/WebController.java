package com.java.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class WebController {

    @GetMapping("/")
    public Mono<String> getHomePage(){
     return Mono.just("home");
    }
}
