package com.java.demo;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class WebController {

    @GetMapping("/hello")
    public String home(Model model) {
        model.addAttribute("message", "Hello Thymeleaf!");
        return "home";
    }
}
