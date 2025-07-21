package com.process.batch.reader;

import com.process.batch.model.Person;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

public class MongoPersonItemReader extends IteratorItemReader<Person> {

    public MongoPersonItemReader(MongoTemplate mongoTemplate) {
        super(fetchPersons(mongoTemplate));
    }

    private static List<Person> fetchPersons(MongoTemplate mongoTemplate) {
        List<Person> person = new ArrayList<>();
        person=mongoTemplate.findAll(Person.class, "persons");
        return person;
    }
}