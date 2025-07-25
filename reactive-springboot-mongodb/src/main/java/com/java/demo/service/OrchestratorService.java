package com.java.demo.service;

import com.java.demo.dto.AvEyeAge;
import com.java.demo.model.Company;
import com.java.demo.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class OrchestratorService {

    @Autowired
    private DependentServiceCaller dependentServiceCaller;

    @Autowired
    private IndependentServiceCaller independentServiceCaller;

    public void executeAll() throws ExecutionException, InterruptedException {
        // Sequential dependent calls

        // Method 1 Calling two Dependend Service (Sequential Flow)

        // Service 1
        dependentServiceCaller.callServiceGetAllPerson().subscribe(person -> {

            // Service 2
            dependentServiceCaller.callServicePersonById(person.getId())
                    .subscribe(p -> {
                        System.out.println("#########################  RESULT FROM (SERVICE1/SERVICE2)   Name :  "+p.getName() + " ------- Age : " + p.getAge()+"#########################");
            });
        });

        System.out.println("=================================================== End of Dependent Service Call - Sequential/Sync (Exceution Order 1) ===================================================");

        // Service 3
        CompletableFuture<AvEyeAge[]> completableFuture_resultAvgAge = independentServiceCaller.getAvgAgeEmployee();

        // Service 4
        CompletableFuture<String[]> completableFuture_resulCountry = independentServiceCaller.getDistinctCountry();

        // Wait to Complete both Service to process
        CompletableFuture.allOf(completableFuture_resultAvgAge, completableFuture_resulCountry).join();
        System.out.println("=================================================== End of Independent Service - Async (Exceution Order 2) =========================================================");

        // After join, retrieve values
        AvEyeAge[] fAvgAge = completableFuture_resultAvgAge.get();
        String[] fCountry= completableFuture_resulCountry.get();

        System.out.println("#########################  RESULT FROM SERVICE3 CompletableFuture getAvgAgeEmployee Service: ###############################");
        for (AvEyeAge age : fAvgAge) {
            System.out.println("Eye Color: " + age.get_id() + ", Avg Age: " + age.getAvgAge());
            System.out.println(age);
        }

        System.out.println("#########################   RESULT FROM SERVICE4  CompletableFuture getDistinctCountry Service: #########################");
        for (String country : fCountry) {
            System.out.println(country);
        }


    }
}