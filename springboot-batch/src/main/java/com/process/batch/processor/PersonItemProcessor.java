package com.process.batch.processor;

import com.process.batch.model.Person;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, String> {
    @Override
    public String process(Person person) {
        System.out.println("------"+person.toString());
        return person.getId() + "," + person.getName() + "," + person.getAge() + "," + person.getGender();
    }
}