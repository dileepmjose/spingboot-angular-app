package com.batch.demo.reader;

import com.batch.demo.model.Person;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.HashMap;
import java.util.Map;

public class PersonItemReader {
    @Bean
    public MongoItemReader<Person> reader(MongoTemplate mongoTemplate) {
        MongoItemReader<Person> reader = new MongoItemReader<>();
        reader.setTemplate(mongoTemplate);
        reader.setQuery("{}");
        reader.setTargetType(Person.class);
        Map<String, Sort.Direction> sort = new HashMap<>();
        sort.put("index", Sort.Direction.ASC);
        reader.setSort(sort);
        return reader;
    }
}