package com.batch.demo.processor;

import com.batch.demo.model.Person;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(Person person) {
        // You can filter/transform here
        return person;
    }
}